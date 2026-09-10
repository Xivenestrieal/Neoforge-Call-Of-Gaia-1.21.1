package net.xivenestriealsilver.call_of_gaia.handlers;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.minecraft.server.level.ServerPlayer;

@EventBusSubscriber(modid = "call_of_gaia")
public class ServerMovementHandler {

    @SubscribeEvent
    public static void onPlayerServerTick(PlayerTickEvent.Post event) {
        // Safely isolate logic to the logical server and ensure it's a ServerPlayer
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {

            // You can call your helper method right here if you want to use it globally,
            // or leave this event open for other global movement checks!
            boolean isMoving = isPlayerMoving(serverPlayer);

        }
    }

    /**
     * Reusable helper method to check if a given server player is moving.
     * Call this from your tarot cards, abilities, or other handlers!
     */
    public static boolean isPlayerMoving(ServerPlayer player) {
        // 1. Calculate positional deltas from the previous tick
        double deltaX = player.getX() - player.xo;
        double deltaY = player.getY() - player.yo;
        double deltaZ = player.getZ() - player.zo;

        // 2. Compute squared horizontal distance to save CPU cycles (avoids Math.sqrt)
        double horizontalMovementSq = (deltaX * deltaX) + (deltaZ * deltaZ);
        double verticalMovement = Math.abs(deltaY);

        // 3. Define micro-thresholds to ignore floating-point inaccuracies
        boolean isMovingHorizontally = horizontalMovementSq > 0.0001;
        boolean isMovingVertically = verticalMovement > 0.0001;

        return isMovingHorizontally || isMovingVertically;
    }
}