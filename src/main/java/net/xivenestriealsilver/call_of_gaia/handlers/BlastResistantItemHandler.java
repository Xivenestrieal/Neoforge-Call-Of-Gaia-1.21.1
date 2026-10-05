package net.xivenestriealsilver.call_of_gaia.handlers;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.xivenestriealsilver.call_of_gaia.item.ModItems;

import java.util.Set;
import java.util.function.Supplier;

@EventBusSubscriber(modid = "call_of_gaia")
public class BlastResistantItemHandler {

    private static final String TARGET_PACKAGE = "net.xivenestriealsilver.call_of_gaia.item.custom.tarot_cards";

    private static final Set<Supplier<Item>> SINGULAR_BLAST_RESISTANT_ITEMS = Set.of(
            ModItems.HOGYOKU_SOUL
    );

    @SubscribeEvent
    public static void onItemExplodeCheck(EntityInvulnerabilityCheckEvent event) {
        if (event.getEntity() instanceof ItemEntity itemEntity) {
            ItemStack stack = itemEntity.getItem();
            Item item = stack.getItem();

            String itemPackage = item.getClass().getPackageName();
            boolean isFromTargetPackage = itemPackage.startsWith(TARGET_PACKAGE);

            boolean isSingularMatch = SINGULAR_BLAST_RESISTANT_ITEMS.stream()
                    .anyMatch(supplier -> supplier.get() == item);

            if (isFromTargetPackage || isSingularMatch) {
                if (event.getSource().is(DamageTypeTags.IS_EXPLOSION)) {
                    event.setInvulnerable(true);
                }
            }
        }
    }
}