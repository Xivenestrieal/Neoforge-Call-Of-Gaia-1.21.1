package net.xivenestriealsilver.call_of_gaia.item.custom.tarot_cards;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import net.xivenestriealsilver.call_of_gaia.handlers.ServerMovementHandler;

public class TheTowerCardItem extends Item implements ICurioItem {

    // Unique IDs for tracking both modifiers independently
    private static final int COOLDOWN_TICKS = 20 * 10;

    private static final ResourceLocation TOWER_ARMOR_ID =
            ResourceLocation.fromNamespaceAndPath("call_of_gaia", "tarot_tower_armor");
    private static final ResourceLocation TOWER_TOUGHNESS_ID =
            ResourceLocation.fromNamespaceAndPath("call_of_gaia", "tarot_tower_toughness");

    public TheTowerCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getCooldowns().isOnCooldown(this)) {
                return;
            }

            AttributeInstance armorAttr = serverPlayer.getAttribute(Attributes.ARMOR);
            AttributeInstance toughnessAttr = serverPlayer.getAttribute(Attributes.ARMOR_TOUGHNESS);
            if (armorAttr == null || toughnessAttr == null) return;

            // 1. Check movement using server movement handler
            boolean isMoving = ServerMovementHandler.isPlayerMoving(serverPlayer);

            // Spawn particles periodically while the player is standing still.
            if (!isMoving && serverPlayer.level().getGameTime() % 5L == 0L) {
                serverPlayer.serverLevel().sendParticles(
                        ParticleTypes.END_ROD,
                        serverPlayer.getX(),
                        serverPlayer.getY() + 1.0D,
                        serverPlayer.getZ(),
                        3,
                        0.35D,
                        0.5D,
                        0.35D,
                        0.01D
                );
            }

            // 2. Determine what the multiplier should be
            double targetAmount = isMoving ? 0.2 : 0.4;

            // 3. Check what modifier is currently applied
            AttributeModifier currentArmorMod = armorAttr.getModifier(TOWER_ARMOR_ID);

            // 4. Only update if they don't have it yet, or if their movement state changed (amount doesn't match)
            if (currentArmorMod == null || currentArmorMod.amount() != targetAmount) {

                // strip old modifiers
                if (currentArmorMod != null) {
                    armorAttr.removeModifier(TOWER_ARMOR_ID);
                    toughnessAttr.removeModifier(TOWER_TOUGHNESS_ID);
                }

                // Apply the new target amounts
                armorAttr.addTransientModifier(new AttributeModifier(
                        TOWER_ARMOR_ID, targetAmount, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                ));
                toughnessAttr.addTransientModifier(new AttributeModifier(
                        TOWER_TOUGHNESS_ID, targetAmount, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                ));
            }
        }
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


            if (slotContext.entity() instanceof ServerPlayer player) {
                player.connection.send(new ClientboundSetTitlesAnimationPacket(30, 40, 30));
                player.connection.send(new ClientboundSetTitleTextPacket(
                        Component.literal("THE TOWER")
                                .withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD)
                ));
                player.connection.send(new ClientboundSetSubtitleTextPacket(
                        Component.literal("the immovable object™")
                                .withStyle(ChatFormatting.BLUE,  ChatFormatting.ITALIC)
                ));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            if (slotContext.entity() instanceof ServerPlayer player) {
                player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
            }

            // Safely clean up both modifiers if unequipped
            AttributeInstance armorAttr = slotContext.entity().getAttribute(Attributes.ARMOR);
            AttributeInstance toughnessAttr = slotContext.entity().getAttribute(Attributes.ARMOR_TOUGHNESS);

            if (armorAttr != null) armorAttr.removeModifier(TOWER_ARMOR_ID);
            if (toughnessAttr != null) toughnessAttr.removeModifier(TOWER_TOUGHNESS_ID);

            slotContext.entity().sendSystemMessage(
                    Component.literal("The Tower's stability crumbles away.")
            );
        }
    }
}