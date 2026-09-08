package net.xivenestriealsilver.call_of_gaia.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class TheFoolCardItem extends Item {
    public boolean foolTarotCardEnabled = false;

    public TheFoolCardItem(Properties properties){super(properties);}

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (foolTarotCardEnabled) {
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        } else {

        }




        return InteractionResult.SUCCESS;
    }
}
