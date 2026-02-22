package net.xivenestriealsilver.soulsmod.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.xivenestriealsilver.soulsmod.util.ModTags;

public class ModToolTiers {
    public static final Tier LAZURITE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_LAZURITE_TOOL,
    1850, 9.0f, 4.0f, 22, () -> Ingredient.of(ModItems.LAZURITE_INGOT));
}
