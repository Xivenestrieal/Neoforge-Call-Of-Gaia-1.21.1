package net.xivenestriealsilver.call_of_gaia.item.custom.boss_items;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class YabachSeekingEyesItem extends Item{
    public YabachSeekingEyesItem(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
            tooltipComponents.add(Component.translatable("tooltip.call_of_gaia.yabach_seeking_eyes"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }

}
