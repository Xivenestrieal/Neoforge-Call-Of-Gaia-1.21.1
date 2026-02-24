import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.xivenestriealsilver.soulsmod.entity.custom.Ent;

public static final RegistryObject<EntityType<Ent>> ENT =
        ENTITY_TYPES.register("ent",
                () -> EntityType.Builder
                        .of(Ent::new, MobCategory.MONSTER)
                        .sized(0.6f, 1.95f) // width, height
                        .build("ent"));

@SubscribeEvent
public static void registerAttributes(EntityAttributeCreationEvent event) {
    event.put(ENT.get(), Ent.createAttributes().build());
}