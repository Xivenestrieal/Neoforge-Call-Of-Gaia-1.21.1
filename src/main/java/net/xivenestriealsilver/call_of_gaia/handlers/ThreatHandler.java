package net.xivenestriealsilver.call_of_gaia.handlers;

import net.minecraft.world.entity.player.Player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ThreatHandler {
    private final Map<UUID, Float> threatMap = new HashMap<>();

    public void increaseThreat(Player player, float amount) {
        UUID id = player.getUUID();
        threatMap.put(id, threatMap.getOrDefault(id, 0f) + amount);
    }

    public void decreaseThreat(Player player, float amount) {
        UUID id = player.getUUID();
        threatMap.put(id, Math.max(0, threatMap.getOrDefault(id, 0f) - amount));
    }

    public void clearThreat(Player player) {
        threatMap.remove(player.getUUID());
    }

    public void clearAll() {
        threatMap.clear();
    }

    public float getThreat(Player player) {
        return threatMap.getOrDefault(player.getUUID(), 0f);
    }

    public Player getHighestThreat(List<Player> players) {
        return players.stream()
                .max(Comparator.comparingDouble(p -> threatMap.getOrDefault(p.getUUID(), 0f)))
                .orElse(null);
    }
}