package net.xivenestriealsilver.call_of_gaia.item.custom.tarot_cards;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class TheWorldCardItem extends Item implements ICurioItem {

    private static final ResourceLocation STRENGTH_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("call_of_gaia", "tarot_strength_boost");

    public TheWorldCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            // Multiplies the total attack damage value by 1.2 (+20%)
            AttributeModifier modifier = new AttributeModifier(
                    STRENGTH_MODIFIER_ID,
                    0.2,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            );

            slotContext.entity().getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(modifier);

            slotContext.entity().sendSystemMessage(
                    Component.literal("You feel the raw power of the tarot card surge through you!")
            );
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            slotContext.entity().getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(STRENGTH_MODIFIER_ID);

            slotContext.entity().sendSystemMessage(
                    Component.literal("The tarot card's power fades away.")
            );
        }
    }
}