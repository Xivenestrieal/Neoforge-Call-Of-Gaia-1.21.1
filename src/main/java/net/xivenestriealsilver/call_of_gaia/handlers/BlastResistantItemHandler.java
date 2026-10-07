package net.xivenestriealsilver.call_of_gaia.handlers;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.xivenestriealsilver.call_of_gaia.util.ModTags;
@EventBusSubscriber(modid = "call_of_gaia")
public class BlastResistantItemHandler {

    @SubscribeEvent
    public static void onItemExplodeCheck(EntityInvulnerabilityCheckEvent event) {
        if (event.getEntity() instanceof ItemEntity itemEntity) {

            if (itemEntity.getItem().is(ModTags.Items.BLAST_RESISTANT_ITEMS)) {
                if (event.getSource().is(DamageTypeTags.IS_EXPLOSION)) {
                    event.setInvulnerable(true);
                }
            }
        }
    }
}