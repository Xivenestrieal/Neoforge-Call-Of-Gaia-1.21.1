package net.xivenestriealsilver.call_of_gaia.item.custom.tarot_cards;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class WheelOfFortuneCardItem extends Item implements ICurioItem {

    private static final int COOLDOWN_TICKS = 20 * 900;

    private static final ResourceLocation LUCK_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("call_of_gaia", "tarot_fortune_luck_boost");

    public WheelOfFortuneCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return !(slotContext.entity() instanceof ServerPlayer player)
                || !player.getCooldowns().isOnCooldown(this);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()
                && slotContext.entity() instanceof ServerPlayer player) {

            if (player.getCooldowns().isOnCooldown(this)) {
                return;
            }

            AttributeInstance luckAttr = player.getAttribute(Attributes.LUCK);

            if (luckAttr != null && luckAttr.getModifier(LUCK_MODIFIER_ID) == null) {
                luckAttr.addTransientModifier(new AttributeModifier(
                        LUCK_MODIFIER_ID,
                        2.0,
                        AttributeModifier.Operation.ADD_VALUE
                ));
            }

            player.connection.send(new ClientboundSetTitlesAnimationPacket(30, 40, 30));
            player.connection.send(new ClientboundSetTitleTextPacket(
                    Component.literal("WHEEL OF FORTUNE")
                            .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD)
            ));
            player.connection.send(new ClientboundSetSubtitleTextPacket(
                    Component.literal("\"LETS GO GAMBLING\"")
                            .withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC)
            ));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()
                && slotContext.entity() instanceof ServerPlayer player) {
            AttributeInstance luckAttr = player.getAttribute(Attributes.LUCK);

            if (luckAttr != null) {
                luckAttr.removeModifier(LUCK_MODIFIER_ID);
            }

            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
            player.sendSystemMessage(
                    Component.literal("RNGeesus frowns.")
            );
        }
    }
}
