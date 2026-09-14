package net.xivenestriealsilver.call_of_gaia.datagen;

import net.minecraft.world.item.Items;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.block.ModBlocks;
import net.xivenestriealsilver.call_of_gaia.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> LAZURITE_SMELTABLES = List.of(ModItems.RAW_LAZURITE,
                ModBlocks.LAZURITE_ORE, ModBlocks.DEEPSLATE_LAZURITE_ORE);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.LAZURITE_SWORD.get())
                .pattern(" BB")
                .pattern(" SB")
                .pattern(" S ")
                .define('B', ModItems.LAZURITE_INGOT.get())
                .define( 'S', Items.STICK)
                .unlockedBy("has_lazurite_ingot", has(ModItems.LAZURITE_INGOT)).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.LAZURITE_HOE.get())
                .pattern(" BB")
                .pattern(" B ")
                .pattern(" S ")
                .define('B', ModItems.LAZURITE_INGOT.get())
                .define( 'S', Items.STICK)
                .unlockedBy("has_lazurite_ingot", has(ModItems.LAZURITE_INGOT)).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.LAZURITE_SHOVEL.get())
                .pattern(" B ")
                .pattern(" S ")
                .pattern(" S ")
                .define('B', ModItems.LAZURITE_INGOT.get())
                .define( 'S', Items.STICK)
                .unlockedBy("has_lazurite_ingot", has(ModItems.LAZURITE_INGOT)).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LAZURITE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.LAZURITE_INGOT.get())
                .unlockedBy("has_lazurite_ingot", has(ModItems.LAZURITE_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LAZURITE_INGOT.get(), 9)
                .requires(ModBlocks.LAZURITE_BLOCK.get())
                .unlockedBy("has_lazurite_block", has(ModBlocks.LAZURITE_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LAZURITE_INGOT.get(), 18)
                .requires(ModBlocks.SEEKING_BLOCK)
                .unlockedBy("has_seeking_block", has(ModBlocks.SEEKING_BLOCK))
                .save(recipeOutput, "call_of_gaia:lazurite_ingot_from_seeking_block");

        oreSmelting(recipeOutput, LAZURITE_SMELTABLES, RecipeCategory.MISC, ModItems.LAZURITE_INGOT.get(), 0.25f, 300, "lazurite_ingot");
        oreBlasting(recipeOutput, LAZURITE_SMELTABLES, RecipeCategory.MISC, ModItems.LAZURITE_INGOT.get(), 0.25f, 150, "lazurite_ingot");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, CallOfGaia.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}