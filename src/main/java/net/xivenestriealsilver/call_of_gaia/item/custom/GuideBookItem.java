package net.xivenestriealsilver.call_of_gaia.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInteractionResult;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.PatchouliAPI;

public class GuideBookItem extends Item {
    public GuideBookItem(Properties props) {
        super(props);
    }

    @Override
    public @NotNull ItemInteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (level.isClientSide) {
            PatchouliAPI.get().openBookGUI(
                ResourceLocation.fromNamespaceAndPath("call_of_gaia", "guide_book")
            );
        }
        return ItemInteractionResult.SUCCESS;
    }
}