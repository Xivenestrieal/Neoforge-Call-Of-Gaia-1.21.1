package net.xivenestriealsilver.call_of_gaia.item.custom.tarot_cards;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class TheHermitCardItem extends Item implements ICurioItem {

    private static final int COOLDOWN_TICKS = 20 * 10;

    private static final ResourceLocation STRENGTH_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("call_of_gaia", "tarot_strength_boost");

    public TheHermitCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return !(slotContext.entity() instanceof ServerPlayer player)
                || !player.getCooldowns().isOnCooldown(this);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            if (slotContext.entity() instanceof ServerPlayer player
                    && player.getCooldowns().isOnCooldown(this)) {
                return;
            }

            // Multiplies the total attack damage value by 1.2 (+20%)
            AttributeModifier modifier = new AttributeModifier(
                    STRENGTH_MODIFIER_ID,
                    0.2,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            );

            slotContext.entity().getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(modifier);

            if (slotContext.entity() instanceof ServerPlayer player) {
                player.connection.send(new ClientboundSetTitlesAnimationPacket(30, 40, 30));
                player.connection.send(new ClientboundSetTitleTextPacket(
                        Component.literal("THE HERMIT")
                                .withStyle(ChatFormatting.DARK_GREEN, ChatFormatting.BOLD)
                ));
                player.connection.send(new ClientboundSetSubtitleTextPacket(
                        Component.literal("y'all ever get lonely?")
                                .withStyle(ChatFormatting.DARK_GREEN,  ChatFormatting.ITALIC)
                ));
            }        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            if (slotContext.entity() instanceof ServerPlayer player) {
                player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
            }

            slotContext.entity().getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(STRENGTH_MODIFIER_ID);

            slotContext.entity().sendSystemMessage(
                    Component.literal("The tarot card's power fades away.")
            );
        }
    }
}