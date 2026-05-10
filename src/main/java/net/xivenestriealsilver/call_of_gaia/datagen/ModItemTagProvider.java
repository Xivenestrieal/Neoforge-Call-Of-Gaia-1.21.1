package net.xivenestriealsilver.call_of_gaia.datagen;

import net.minecraft.tags.ItemTags;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.item.ModItems;
import net.xivenestriealsilver.call_of_gaia.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, CallOfGaia.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.LAZURITE_INGOT.get())
                .add(ModItems.RAW_LAZURITE.get())
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.COMPASS);

        tag(ItemTags.SWORDS)
                .add(ModItems.LAZURITE_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.LAZURITE_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.LAZURITE_SHOVEL.get());
        tag(ItemTags.AXES)
                .add(ModItems.LAZURITE_AXE.get());
        tag(ItemTags.HOES)
                .add(ModItems.LAZURITE_HOE.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.LAZURITE_HELMET.get())
                .add(ModItems.LAZURITE_CHESTPLATE.get())
                .add(ModItems.LAZURITE_LEGGINGS.get())
                .add(ModItems.LAZURITE_BOOTS.get());




    }
}