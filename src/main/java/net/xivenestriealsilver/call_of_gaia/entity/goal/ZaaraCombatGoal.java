package net.xivenestriealsilver.call_of_gaia.entity.goal;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.xivenestriealsilver.call_of_gaia.effect.ModEffects;
import net.xivenestriealsilver.call_of_gaia.entity.custom.ZaaraEntity;
import net.xivenestriealsilver.call_of_gaia.util.StatusBuildupManager;
import net.xivenestriealsilver.call_of_gaia.util.StatusType;

import java.util.EnumSet;
import java.util.List;

public class ZaaraCombatGoal extends Goal {

    private final ZaaraEntity zaara;
    private int attackCooldown = 0;
    private int attackPhaseTimer = 0;
    private AttackPhase currentAttack = AttackPhase.NONE;

    private static final float MELEE_RANGE = 4.0f;
    private static final float SHORT_RANGE = 8.0f;
    private static final float MEDIUM_RANGE = 20.0f;
    private static final float LONG_RANGE = 40.0f;

    public enum AttackPhase {
        NONE,
        STABBY_STABBY_VOLLEY_1,
        STABBY_STABBY_ICE_SLASH,
        STABBY_STABBY_VOLLEY_2,
        STABBY_STABBY_FEINT,
        SHATTERING_WINDUP,
        SHATTERING_MIST,
        SHATTERING_SHATTER,
        ICE_SPEAR_VOLLEY_WINDUP,
        ICE_SPEAR_VOLLEY_LAUNCH,
        FROZEN_MONARCH_TRINKET,
        FROZEN_MONARCH_FREEZE_SWORD,
        FROZEN_MONARCH_STORM,
        FROZEN_MONARCH_SHATTER,
        REALITY_SHATTER_LUNGE,
        REALITY_SHATTER_VACUUM,
        REALITY_SHATTER_CLONE,
        GREAT_ICE_SPEAR_WINDUP,
        GREAT_ICE_SPEAR_THROW,
        GREAT_ICE_SPEAR_EXPLOSION
    }

    public ZaaraCombatGoal(ZaaraEntity zaara) {
        this.zaara = zaara;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return this.zaara.getTarget() != null;
    }

    @Override
    public void tick() {
        LivingEntity target = this.zaara.getTarget();
        if (target == null) return;
        this.zaara.getLookControl().setLookAt(target);
        if (currentAttack == AttackPhase.NONE) {
            if (attackCooldown > 0) {
                attackCooldown--;
                if (attackCooldown == 0) trySelectNewTarget();
                return;
            }
            chooseNextAttack();
        } else {
            tickCurrentAttack(target);
        }
    }

    private void trySelectNewTarget() {
        List<Player> nearbyPlayers = zaara.level().getEntitiesOfClass(Player.class, new AABB(zaara.blockPosition()).inflate(40));
        if (nearbyPlayers.isEmpty()) return;
        float totalThreat = 0f;
        for (Player player : nearbyPlayers) totalThreat += zaara.getThreatManager().getThreat(player) + 1f;
        float roll = zaara.getRandom().nextFloat() * totalThreat;
        float cumulative = 0f;
        for (Player player : nearbyPlayers) {
            cumulative += zaara.getThreatManager().getThreat(player) + 1f;
            if (roll <= cumulative) { zaara.setTarget(player); return; }
        }
    }

    private void chooseNextAttack() {
        LivingEntity target = zaara.getTarget();
        if (target == null) return;
        double distance = zaara.distanceTo(target);
        double healthPercent = zaara.getHealth() / zaara.getMaxHealth();
        if (!zaara.isPhaseTwo() && healthPercent <= 0.5) { startAttack(AttackPhase.FROZEN_MONARCH_TRINKET, 0); return; }
        if (!zaara.isPhaseTwo()) choosePhaseOneAttack(distance); else choosePhaseTwoAttack(distance);
    }

    private void choosePhaseOneAttack(double distance) {
        if (distance <= MELEE_RANGE) {
            int roll = zaara.getRandom().nextInt(10);
            if (roll < 6) startAttack(AttackPhase.STABBY_STABBY_VOLLEY_1, 0);
            else if (roll < 8) startAttack(AttackPhase.SHATTERING_WINDUP, 0);
            else startAttack(AttackPhase.ICE_SPEAR_VOLLEY_WINDUP, 0);
        } else if (distance <= SHORT_RANGE) {
            int roll = zaara.getRandom().nextInt(10);
            if (roll < 4) startAttack(AttackPhase.STABBY_STABBY_VOLLEY_1, 0);
            else if (roll < 7) startAttack(AttackPhase.SHATTERING_WINDUP, 0);
            else startAttack(AttackPhase.ICE_SPEAR_VOLLEY_WINDUP, 0);
        } else if (distance <= MEDIUM_RANGE) {
            int roll = zaara.getRandom().nextInt(10);
            if (roll < 2) startAttack(AttackPhase.STABBY_STABBY_VOLLEY_1, 0);
            else if (roll < 6) startAttack(AttackPhase.SHATTERING_WINDUP, 0);
            else startAttack(AttackPhase.ICE_SPEAR_VOLLEY_WINDUP, 0);
        } else startAttack(AttackPhase.ICE_SPEAR_VOLLEY_WINDUP, 0);
    }

    private void choosePhaseTwoAttack(double distance) {
        if (distance <= MELEE_RANGE) {
            int roll = zaara.getRandom().nextInt(10);
            if (roll < 7) startAttack(AttackPhase.REALITY_SHATTER_LUNGE, 0);
            else startAttack(AttackPhase.GREAT_ICE_SPEAR_WINDUP, 0);
        } else if (distance <= MEDIUM_RANGE) {
            int roll = zaara.getRandom().nextInt(10);
            if (roll < 5) startAttack(AttackPhase.REALITY_SHATTER_LUNGE, 0);
            else startAttack(AttackPhase.GREAT_ICE_SPEAR_WINDUP, 0);
        } else if (distance <= LONG_RANGE) startAttack(AttackPhase.GREAT_ICE_SPEAR_WINDUP, 0);
        else { zaara.getNavigation().moveTo(zaara.getTarget(), 1.2); attackCooldown = 10; }
    }

    private void tickCurrentAttack(LivingEntity target) {
        attackPhaseTimer--;
        switch (currentAttack) {
            case STABBY_STABBY_VOLLEY_1 -> {
                if (attackPhaseTimer <= 0) {
                    performMeleeVolley(target, 4, 3.0f, false, 0f);
                    startAttack(AttackPhase.STABBY_STABBY_ICE_SLASH, 20);
                }
            }
            case STABBY_STABBY_ICE_SLASH -> {
                if (attackPhaseTimer <= 0) {
                    performMeleeHit(target, 2.0f);
                    applyFrostbite(target, 50f);
                    knockUp(target, 0.6);
                    startAttack(AttackPhase.STABBY_STABBY_VOLLEY_2, 30);
                }
            }
            case STABBY_STABBY_VOLLEY_2 -> {
                if (attackPhaseTimer <= 0) {
                    performMeleeVolley(target, 4, 3.0f, true, 10f);
                    startAttack(AttackPhase.STABBY_STABBY_FEINT, 15);
                }
            }
            case STABBY_STABBY_FEINT -> {
                if (attackPhaseTimer <= 0) {
                    performMeleeHit(target, 2.0f);
                    stepBack();
                    endAttack(40);
                }
            }
            case SHATTERING_WINDUP -> { if (attackPhaseTimer <= 0) { zaara.startShatteringWindup(); startAttack(AttackPhase.SHATTERING_MIST, 40); } }
            case SHATTERING_MIST -> { if (attackPhaseTimer <= 0) { shootFrostMistCone(); startAttack(AttackPhase.SHATTERING_SHATTER, 30); } }
            case SHATTERING_SHATTER -> { if (attackPhaseTimer <= 0) { shatterMist(); endAttack(60); } }
            case ICE_SPEAR_VOLLEY_WINDUP -> { if (attackPhaseTimer <= 0) { zaara.startSpearVolleyWindup(); startAttack(AttackPhase.ICE_SPEAR_VOLLEY_LAUNCH, 30); } }
            case ICE_SPEAR_VOLLEY_LAUNCH -> { if (attackPhaseTimer <= 0) { launchIceSpearVolley(target); endAttack(50); } }
            case FROZEN_MONARCH_TRINKET -> { if (attackPhaseTimer <= 0) { zaara.setInvulnerable(true); startAttack(AttackPhase.FROZEN_MONARCH_FREEZE_SWORD, 40); } }
            case FROZEN_MONARCH_FREEZE_SWORD -> { if (attackPhaseTimer <= 0) { freezeAroundZaara(); startAttack(AttackPhase.FROZEN_MONARCH_STORM, 40); } }
            case FROZEN_MONARCH_STORM -> { if (attackPhaseTimer <= 0) { spawnIceStorm(); startAttack(AttackPhase.FROZEN_MONARCH_SHATTER, 60); } }
            case FROZEN_MONARCH_SHATTER -> { if (attackPhaseTimer <= 0) { shatterArenaSpikes(); zaara.setInvulnerable(false); zaara.setPhaseTwo(true); endAttack(80); } }
            case REALITY_SHATTER_LUNGE -> {
                if (attackPhaseTimer <= 0) {
                    lungeAtTarget(target);
                    if (zaara.getRandom().nextBoolean()) startAttack(AttackPhase.REALITY_SHATTER_VACUUM, 20);
                    else startAttack(AttackPhase.REALITY_SHATTER_CLONE, 20);
                }
            }
            case REALITY_SHATTER_VACUUM -> { if (attackPhaseTimer <= 0) { pullTargetToward(target); endAttack(40); } }
            case REALITY_SHATTER_CLONE -> { if (attackPhaseTimer <= 0) { spawnCloneAndTeleport(target); endAttack(40); } }
            case GREAT_ICE_SPEAR_WINDUP -> { if (attackPhaseTimer <= 0) { zaara.startGreatSpearWindup(); startAttack(AttackPhase.GREAT_ICE_SPEAR_THROW, 60); } }
            case GREAT_ICE_SPEAR_THROW -> { if (attackPhaseTimer <= 0) { throwGreatIceSpear(target); startAttack(AttackPhase.GREAT_ICE_SPEAR_EXPLOSION, 40); } }
            case GREAT_ICE_SPEAR_EXPLOSION -> { if (attackPhaseTimer <= 0) { explodeIceSpear(); endAttack(60); } }
            default -> endAttack(20);
        }
    }

    private void startAttack(AttackPhase phase, int duration) { this.currentAttack = phase; this.attackPhaseTimer = duration; }
    private void endAttack(int cooldown) { this.currentAttack = AttackPhase.NONE; this.attackCooldown = cooldown; }
    private void performMeleeHit(LivingEntity target, float damage) { if (zaara.distanceTo(target) <= MELEE_RANGE) target.hurt(zaara.damageSources().mobAttack(zaara), damage); }

    private void performMeleeVolley(LivingEntity target, int hits, float damagePerHit, boolean frost, float buildup) {
        for (int i = 0; i < hits; i++) {
            performMeleeHit(target, damagePerHit);
            if (frost) applyFrostbite(target, buildup);
        }
    }

    private void applyFrostbite(LivingEntity target, float buildup) {
        StatusBuildupManager.get().addBuildup(target, StatusType.FROSTBITE, buildup);
    }

    private void knockUp(LivingEntity target, double strength) { target.setDeltaMovement(target.getDeltaMovement().add(0, strength, 0)); }
    private void stepBack() { Vec3 lookDir = zaara.getLookAngle(); zaara.setDeltaMovement(lookDir.scale(-0.5)); }
    private void lungeAtTarget(LivingEntity target) { Vec3 direction = target.position().subtract(zaara.position()).normalize(); zaara.setDeltaMovement(direction.scale(1.5)); performMeleeHit(target, 8.0f); }
    private void pullTargetToward(LivingEntity target) { Vec3 direction = zaara.position().subtract(target.position()).normalize(); target.setDeltaMovement(direction.scale(1.5)); }

    private List<Player> getPlayersInCone(float range, float angleDegrees) {
        Vec3 lookDir = zaara.getLookAngle();
        return zaara.level().getEntitiesOfClass(Player.class, new AABB(zaara.blockPosition()).inflate(range),
                player -> {
                    Vec3 toPlayer = player.position().subtract(zaara.position()).normalize();
                    return Math.toDegrees(Math.acos(lookDir.dot(toPlayer))) <= angleDegrees / 2;
                });
    }

    private void shootFrostMistCone() { for (Player p : getPlayersInCone(10.0f, 60.0f)) { p.hurt(zaara.damageSources().magic(), 3.0f); applyFrostbite(p, 20f); } }
    private void shatterMist() { for (Player p : getPlayersInCone(10.0f, 60.0f)) { p.hurt(zaara.damageSources().magic(), 8.0f); applyFrostbite(p, 10f); } }
    private void launchIceSpearVolley(LivingEntity target) { if (zaara.distanceTo(target) <= MEDIUM_RANGE) { target.hurt(zaara.damageSources().magic(), 5.0f); applyFrostbite(target, 25f); } }
    private void throwGreatIceSpear(LivingEntity target) { if (zaara.distanceTo(target) >= SHORT_RANGE && zaara.distanceTo(target) <= LONG_RANGE) { target.hurt(zaara.damageSources().magic(), 12.0f); applyFrostbite(target, 30f); } }
    private void explodeIceSpear() { for (Player p : zaara.level().getEntitiesOfClass(Player.class, new AABB(zaara.blockPosition()).inflate(5))) { p.hurt(zaara.damageSources().magic(), 4.0f); applyFrostbite(p, 50f); } }
    private void freezeAroundZaara() { for (Player p : zaara.level().getEntitiesOfClass(Player.class, new AABB(zaara.blockPosition()).inflate(10))) { applyFrostbite(p, 20f); } }
    private void spawnIceStorm() { for (Player p : zaara.level().getEntitiesOfClass(Player.class, new AABB(zaara.blockPosition()).inflate(15))) { applyFrostbite(p, 20f); } }
    private void shatterArenaSpikes() { for (Player p : zaara.level().getEntitiesOfClass(Player.class, new AABB(zaara.blockPosition()).inflate(15))) { p.hurt(zaara.damageSources().magic(), 15.0f); applyFrostbite(p, 100f); } }
    private void spawnCloneAndTeleport(LivingEntity target) { Vec3 b = target.position().subtract(target.getLookAngle().scale(2)); zaara.teleportTo(b.x, b.y, b.z); performMeleeHit(target, 10.0f); }
}