package net.xivenestriealsilver.call_of_gaia.event;

import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.entity.ModEntities;
import net.xivenestriealsilver.call_of_gaia.entity.client.GeckoModel;
import net.xivenestriealsilver.call_of_gaia.entity.custom.GeckoEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.xivenestriealsilver.call_of_gaia.entity.custom.ZaaraEntity;

@EventBusSubscriber(modid = CallOfGaia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GECKO.get(), GeckoEntity.createAttributes().build());
        event.put(ModEntities.ZAARA.get(), ZaaraEntity.createAttributes().build());
    }
}