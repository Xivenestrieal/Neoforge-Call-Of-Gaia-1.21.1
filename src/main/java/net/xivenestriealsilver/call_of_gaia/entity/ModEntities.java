package net.xivenestriealsilver.call_of_gaia.entity;

import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.entity.custom.GeckoEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xivenestriealsilver.call_of_gaia.entity.custom.ZaaraEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CallOfGaia.MOD_ID);

    public static final Supplier<EntityType<GeckoEntity>> GECKO =
            ENTITY_TYPES.register("gecko", () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.MONSTER)
                    .sized(0.75f, 0.35f).build("gecko"));

    public static final Supplier<EntityType<ZaaraEntity>> ZAARA =
            ENTITY_TYPES.register("zaara", () -> EntityType.Builder.of(ZaaraEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2.5f).build("zaara"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}