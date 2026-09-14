package net.xivenestriealsilver.call_of_gaia.event;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.entity.client.GeckoModel;
import net.xivenestriealsilver.call_of_gaia.entity.client.IceSpearProjectileModel;
import net.xivenestriealsilver.call_of_gaia.entity.client.ZaaraModel;

@EventBusSubscriber(modid = CallOfGaia.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventBusEvents {
    private ClientModEventBusEvents() {
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
        event.registerLayerDefinition(IceSpearProjectileModel.LAYER_LOCATION, IceSpearProjectileModel::createBodyLayer);
        event.registerLayerDefinition(ZaaraModel.LAYER_LOCATION, ZaaraModel::createBodyLayer);
    }
}
