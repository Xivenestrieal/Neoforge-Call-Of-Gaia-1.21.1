package net.xivenestriealsilver.call_of_gaia.handlers;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.minecraft.server.level.ServerPlayer;

@EventBusSubscriber(modid = "call_of_gaia")
public class ServerMovementHandler {

    @SubscribeEvent
    public static void onPlayerServerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {

            boolean isMoving = isPlayerMoving(serverPlayer);

        }
    }


    //  Reusable helper method to check if a given server player is moving.

    public static boolean isPlayerMoving(ServerPlayer player) {
        // walkDist is updated when the player walks or runs
        boolean isWalking = Math.abs(player.walkDist - player.walkDistO) > 0.001F;
        boolean hasHorizontalVelocity = player.getDeltaMovement().horizontalDistanceSqr() > 0.0001D;

        return isWalking || hasHorizontalVelocity;
    }
}