package net.xivenestriealsilver.soulsmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.xivenestriealsilver.soulsmod.SoulsMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_LAZURITE_TOOL = createTag("needs_lazurite_tool");
        public static final TagKey<Block> INCORRECT_FOR_LAZURITE_TOOL = createTag("incorrect_for_lazurite_tool");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(SoulsMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(SoulsMod.MOD_ID, name));
        }
    }
}
