package net.xivenestriealsilver.call_of_gaia.handlers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.xivenestriealsilver.call_of_gaia.effect.ModEffects;
import net.xivenestriealsilver.call_of_gaia.util.StatusType;

import java.util.*;

public class StatusBuildupHandler {
    private static final StatusBuildupHandler INSTANCE = new StatusBuildupHandler();
    public static StatusBuildupHandler get() { return INSTANCE; }

    private static final float PROC_THRESHOLD = 100f;
    private final Map<UUID, Map<StatusType, Float>> buildupMap = new HashMap<>();

    public void addBuildup(LivingEntity entity, StatusType type, float amount) {
        UUID id = entity.getUUID();
        buildupMap.computeIfAbsent(id, k -> new HashMap<>());
        Map<StatusType, Float> map = buildupMap.get(id);
        float newAmount = map.getOrDefault(type, 0f) + amount;

        if (newAmount >= PROC_THRESHOLD) {
            procStatus(entity, type);
            map.put(type, 0f);
        } else {
            map.put(type, newAmount);
        }
    }

    private void procStatus(LivingEntity entity, StatusType type) {
        if (type == StatusType.FROSTBITE) {
            entity.addEffect(new MobEffectInstance(ModEffects.FROSTBITE_EFFECT, 120, 1));
        }
    }

    public void tick(Player player) {

    }
}