package net.xivenestriealsilver.call_of_gaia.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer; // Add this import
import vazkii.patchouli.api.PatchouliAPI;

public class GuideBookItem extends Item {

    public GuideBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            ResourceLocation bookId = ResourceLocation.fromNamespaceAndPath("call_of_gaia", "guide_book");

            PatchouliAPI.get().openBookGUI((ServerPlayer) player, bookId);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}