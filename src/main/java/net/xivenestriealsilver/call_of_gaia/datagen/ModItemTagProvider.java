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

        tag(ModTags.Items.CARD_DECK)
                .add(ModItems.THE_FOOL_CARD.get())
                .add(ModItems.THE_MAGICIAN_CARD.get())
                .add(ModItems.THE_HIGH_PRIESTESS_CARD.get())
                .add(ModItems.THE_EMPRESS_CARD.get())
                .add(ModItems.THE_EMPEROR_CARD.get())
                .add(ModItems.THE_HIEROPHANT_CARD.get())
                .add(ModItems.THE_LOVERS_CARD.get())
                .add(ModItems.THE_CHARIOT_CARD.get())
                .add(ModItems.STRENGTH_CARD.get())
                .add(ModItems.THE_HERMIT_CARD.get())
                .add(ModItems.WHEEL_OF_FORTUNE_CARD.get())
                .add(ModItems.JUSTICE_CARD.get())
                .add(ModItems.THE_HANGED_MAN_CARD.get())
                .add(ModItems.DEATH_CARD.get())
                .add(ModItems.TEMPERANCE_CARD.get())
                .add(ModItems.THE_DEVIL_CARD.get())
                .add(ModItems.THE_TOWER_CARD.get())
                .add(ModItems.THE_STAR_CARD.get())
                .add(ModItems.THE_MOON_CARD.get())
                .add(ModItems.THE_SUN_CARD.get())
                .add(ModItems.JUDGEMENT_CARD.get())
                .add(ModItems.THE_WORLD_CARD.get());

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

        tag(ModTags.Items.BLAST_RESISTANT_ITEMS)
                .add(ModItems.CORRUPTED_SOUL.get())
                .add(ModItems.TAINTED_EVOLUTION_AWAKENING.get())
                .add(ModItems.PURE_EVOLUTION_AWAKENING.get())
                .add(ModItems.HOGYOKU_SOUL.get())
                .add(ModItems.THE_FOOL_CARD.get())
                .add(ModItems.THE_MAGICIAN_CARD.get())
                .add(ModItems.THE_HIGH_PRIESTESS_CARD.get())
                .add(ModItems.THE_EMPRESS_CARD.get())
                .add(ModItems.THE_EMPEROR_CARD.get())
                .add(ModItems.THE_HIEROPHANT_CARD.get())
                .add(ModItems.THE_LOVERS_CARD.get())
                .add(ModItems.THE_CHARIOT_CARD.get())
                .add(ModItems.STRENGTH_CARD.get())
                .add(ModItems.THE_HERMIT_CARD.get())
                .add(ModItems.WHEEL_OF_FORTUNE_CARD.get())
                .add(ModItems.JUSTICE_CARD.get())
                .add(ModItems.THE_HANGED_MAN_CARD.get())
                .add(ModItems.DEATH_CARD.get())
                .add(ModItems.TEMPERANCE_CARD.get())
                .add(ModItems.THE_DEVIL_CARD.get())
                .add(ModItems.THE_TOWER_CARD.get())
                .add(ModItems.THE_STAR_CARD.get())
                .add(ModItems.THE_MOON_CARD.get())
                .add(ModItems.THE_SUN_CARD.get())
                .add(ModItems.JUDGEMENT_CARD.get())
                .add(ModItems.THE_WORLD_CARD.get());




        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.LAZURITE_HELMET.get())
                .add(ModItems.LAZURITE_CHESTPLATE.get())
                .add(ModItems.LAZURITE_LEGGINGS.get())
                .add(ModItems.LAZURITE_BOOTS.get());




    }
}