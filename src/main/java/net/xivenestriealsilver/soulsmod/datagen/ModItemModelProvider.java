package net.xivenestriealsilver.soulsmod.datagen;

import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.xivenestriealsilver.soulsmod.SoulsMod;
import net.xivenestriealsilver.soulsmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SoulsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.LAZURITE_INGOT.get());
        basicItem(ModItems.RAW_LAZURITE.get());

        basicItem(ModItems.HOGYOKU_SOUL.get());
        basicItem(ModItems.CORRUPTED_SOUL.get());

        basicItem(ModItems.FROZEN_FLAME.get());
        basicItem(ModItems.ROOT_HEART.get());
        basicItem(ModItems.ENT_CHARCOAL.get());

        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.SEEKING_EYES.get());
        basicItem(ModItems.LAZURITE_KEY.get());

        handheldItem(ModItems.LAZURITE_SWORD);
        handheldItem(ModItems.LAZURITE_PICKAXE);
        handheldItem(ModItems.LAZURITE_SHOVEL);
        handheldItem(ModItems.LAZURITE_AXE);
        handheldItem(ModItems.LAZURITE_HOE);

    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SoulsMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SoulsMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(SoulsMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(SoulsMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}