package net.xivenestriealsilver.call_of_gaia.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.xivenestriealsilver.call_of_gaia.entity.custom.zaara.ZaaraEntity;
import net.xivenestriealsilver.call_of_gaia.entity.goal.attacks.zaara.GreatIceSpearAttack;
import net.xivenestriealsilver.call_of_gaia.util.ThreatManager;

import java.util.EnumSet;
import java.util.List;

public class ZaaraCombatGoal extends Goal {
    private final ZaaraEntity zaara;
    private ZaaraAttack currentAttack = null;
    private int attackCooldown = 0;

    public ZaaraCombatGoal(ZaaraEntity zaara) {
        this.zaara = zaara;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return true; // Always running to keep updating threat/attacks
    }

    @Override
    public void tick() {
        // 1. Target Management (Threat Logic)
        List<Player> nearbyPlayers = this.zaara.level().getEntitiesOfClass(Player.class, this.zaara.getBoundingBox().inflate(16.0));

        if (nearbyPlayers.isEmpty()) {
            this.zaara.setTarget(null);
            return;
        }

        Player highestThreatPlayer = ThreatManager.get().getHighestThreat(nearbyPlayers);
        if (highestThreatPlayer != null && highestThreatPlayer != this.zaara.getTarget()) {
            this.zaara.setTarget(highestThreatPlayer);
        }

        // 2. Attack Execution Logic
        LivingEntity target = this.zaara.getTarget();
        if (target == null) return;

        this.zaara.getLookControl().setLookAt(target);

        if (currentAttack != null) {
            currentAttack.tick(zaara, target);
            if (currentAttack.isFinished()) {
                currentAttack = null;
                attackCooldown = 40;
            }
        } else {
            if (attackCooldown > 0) {
                attackCooldown--;
            } else {
                selectAttack(target);
            }
        }
    }

    private void selectAttack(LivingEntity target) {
        if (zaara.isPhaseTwo()) {
            this.currentAttack = new GreatIceSpearAttack();
            this.currentAttack.onStart(zaara, target);
        }
    }
}