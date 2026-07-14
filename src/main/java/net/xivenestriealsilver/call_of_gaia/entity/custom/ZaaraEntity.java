package net.xivenestriealsilver.call_of_gaia.entity.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.xivenestriealsilver.call_of_gaia.entity.goal.ZaaraCombatGoal;
import net.xivenestriealsilver.call_of_gaia.util.ThreatManager;

public class ZaaraEntity extends Monster {

    private static final EntityDataAccessor<Boolean> PHASE_TWO =
            SynchedEntityData.defineId(ZaaraEntity.class, EntityDataSerializers.BOOLEAN);

    private  final ServerBossEvent bossEvent = new ServerBossEvent(Component.literal("Zaara, Monarch Of Frost"),
            BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_10);

    private final ThreatManager threatManager = new ThreatManager();

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState shatteringWindupAnimationState = new AnimationState();
    public final AnimationState spearVolleyWindupAnimationState = new AnimationState();
    public final AnimationState greatSpearWindupAnimationState = new AnimationState();
    public final AnimationState frozenMonarchAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    public ZaaraEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(PHASE_TWO, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ZaaraCombatGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 16.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 300D)
                .add(Attributes.MOVEMENT_SPEED, 1D)
                .add(Attributes.FOLLOW_RANGE, 40D)
                .add(Attributes.ATTACK_DAMAGE, 8D)
                .add(Attributes.ARMOR, 10D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.9D);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && source.getEntity() instanceof Player player) {
            threatManager.increaseThreat(player, amount);
        }
        return result;
    }

    public ThreatManager getThreatManager() {
        return threatManager;
    }

    public boolean isPhaseTwo() {
        return this.entityData.get(PHASE_TWO);
    }

    public void setPhaseTwo(boolean phaseTwo) {
        this.entityData.set(PHASE_TWO, phaseTwo);
    }

    public void startShatteringWindup() {
        this.shatteringWindupAnimationState.start(this.tickCount);
    }

    public void startSpearVolleyWindup() {
        this.spearVolleyWindupAnimationState.start(this.tickCount);
    }

    public void startGreatSpearWindup() {
        this.greatSpearWindupAnimationState.start(this.tickCount);
    }

    public void stopAllAnimations() {
        this.shatteringWindupAnimationState.stop();
        this.spearVolleyWindupAnimationState.stop();
        this.greatSpearWindupAnimationState.stop();
        this.frozenMonarchAnimationState.stop();
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    //Boss Bar


    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}