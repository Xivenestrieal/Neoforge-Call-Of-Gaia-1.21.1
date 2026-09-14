package net.xivenestriealsilver.call_of_gaia.handlers;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;

@EventBusSubscriber(modid = CallOfGaia.MOD_ID)
public class BuildupEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide()) {
            StatusBuildupHandler.get().tick(player);
        }
    }
}