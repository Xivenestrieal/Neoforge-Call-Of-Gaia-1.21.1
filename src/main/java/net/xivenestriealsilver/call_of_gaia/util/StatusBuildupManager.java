package net.xivenestriealsilver.call_of_gaia.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.xivenestriealsilver.call_of_gaia.effect.ModEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatusBuildupManager {

    private static final StatusBuildupManager INSTANCE = new StatusBuildupManager();

    public static StatusBuildupManager get() {
        return INSTANCE;
    }

    private static final float PROC_THRESHOLD = 100f;

    private static final Map<StatusType, Float> DRAIN_PER_SECOND = Map.of(
            StatusType.BLEED, 8f,
            StatusType.FROSTBITE, 6f
    );

    private static final Map<StatusType, Integer> PROC_EFFECT_DURATION = Map.of(
            StatusType.BLEED, 80,
            StatusType.FROSTBITE, 120
    );

    private static final Map<StatusType, Integer> PROC_EFFECT_AMPLIFIER = Map.of(
            StatusType.BLEED, 0,
            StatusType.FROSTBITE, 1
    );

    private final Map<UUID, Map<StatusType, Float>> buildupMap = new HashMap<>();
    private final Map<UUID, Integer> tickMap = new HashMap<>();

    public void addBuildup(LivingEntity entity, StatusType type, float amount) {
        UUID id = entity.getUUID();
        buildupMap.computeIfAbsent(id, k -> new HashMap<>());
        Map<StatusType, Float> entityBuildup = buildupMap.get(id);

        float current = entityBuildup.getOrDefault(type, 0f);
        float newAmount = current + amount;

        if (newAmount >= PROC_THRESHOLD) {
            procStatus(entity, type);
            entityBuildup.put(type, 0f);
        } else {
            entityBuildup.put(type, newAmount);
        }
    }

    public float getBuildup(LivingEntity entity, StatusType type) {
        UUID id = entity.getUUID();
        if (!buildupMap.containsKey(id)) return 0f;
        return buildupMap.get(id).getOrDefault(type, 0f);
    }

    public void clearBuildup(LivingEntity entity, StatusType type) {
        UUID id = entity.getUUID();
        if (buildupMap.containsKey(id)) {
            buildupMap.get(id).put(type, 0f);
        }
    }

    public void clearAll(LivingEntity entity) {
        buildupMap.remove(entity.getUUID());
    }

    public void tick(LivingEntity entity) {
        UUID id = entity.getUUID();
        if (!buildupMap.containsKey(id)) return;

        int ticks = tickMap.getOrDefault(id, 0) + 1;
        tickMap.put(id, ticks);

        if (ticks % 20 == 0) {
            Map<StatusType, Float> entityBuildup = buildupMap.get(id);
            for (StatusType type : StatusType.values()) {
                float current = entityBuildup.getOrDefault(type, 0f);
                if (current > 0) {
                    float drained = Math.max(0, current - DRAIN_PER_SECOND.get(type));
                    entityBuildup.put(type, drained);
                }
            }
        }
    }

    private void procStatus(LivingEntity entity, StatusType type) {
        int duration = PROC_EFFECT_DURATION.get(type);
        int amplifier = PROC_EFFECT_AMPLIFIER.get(type);

        switch (type) {
            case BLEED -> entity.addEffect(
                    new MobEffectInstance(ModEffects.BLEED_EFFECT, duration, amplifier));
            case FROSTBITE -> entity.addEffect(
                    new MobEffectInstance(ModEffects.FROSTBITE_EFFECT, duration, amplifier));
        }
    }
}