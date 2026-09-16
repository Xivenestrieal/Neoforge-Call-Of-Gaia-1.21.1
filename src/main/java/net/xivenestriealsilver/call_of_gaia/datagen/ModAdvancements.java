package net.xivenestriealsilver.call_of_gaia.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.xivenestriealsilver.call_of_gaia.item.ModItems;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class ModAdvancements extends AdvancementProvider {
    public ModAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new CallOfGaiaModAdvancements()));
    }

    public static class CallOfGaiaModAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            Advancement.Builder.advancement()
                    .display(
                            ModItems.LAZURITE_INGOT,
                            Component.translatable("advancements.call_of_gaia.root.title"),
                            Component.translatable("advancements.call_of_gaia.root.description"),
                            ResourceLocation.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            false,
                            false,
                            false
                    )
                    .addCriterion("has_lazurite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAZURITE_INGOT.get()))
                    .save(output, CallOfGaia.MOD_ID + ":root");

            CardAdvancement[] cards = {
                    new CardAdvancement(ModItems.THE_FOOL_CARD, "the_fool"),
                    new CardAdvancement(ModItems.THE_MAGICIAN_CARD, "the_magician"),
                    new CardAdvancement(ModItems.THE_HIGH_PRIESTESS_CARD, "the_high_priestess"),
                    new CardAdvancement(ModItems.THE_EMPRESS_CARD, "the_empress"),
                    new CardAdvancement(ModItems.THE_EMPEROR_CARD, "the_emperor"),
                    new CardAdvancement(ModItems.THE_HIEROPHANT_CARD, "the_hierophant"),
                    new CardAdvancement(ModItems.THE_LOVERS_CARD, "the_lovers"),
                    new CardAdvancement(ModItems.THE_CHARIOT_CARD, "the_chariot"),
                    new CardAdvancement(ModItems.STRENGTH_CARD, "strength"),
                    new CardAdvancement(ModItems.THE_HERMIT_CARD, "the_hermit"),
                    new CardAdvancement(ModItems.WHEEL_OF_FORTUNE_CARD, "wheel_of_fortune"),
                    new CardAdvancement(ModItems.JUSTICE_CARD, "justice"),
                    new CardAdvancement(ModItems.THE_HANGED_MAN_CARD, "the_hanged_man"),
                    new CardAdvancement(ModItems.DEATH_CARD, "death"),
                    new CardAdvancement(ModItems.TEMPERANCE_CARD, "temperance"),
                    new CardAdvancement(ModItems.THE_DEVIL_CARD, "the_devil"),
                    new CardAdvancement(ModItems.THE_TOWER_CARD, "the_tower"),
                    new CardAdvancement(ModItems.THE_STAR_CARD, "the_star"),
                    new CardAdvancement(ModItems.THE_MOON_CARD, "the_moon"),
                    new CardAdvancement(ModItems.THE_SUN_CARD, "the_sun"),
                    new CardAdvancement(ModItems.JUDGEMENT_CARD, "judgement"),
                    new CardAdvancement(ModItems.THE_WORLD_CARD, "the_world")
            };

            AdvancementHolder majorArcanaRoot = Advancement.Builder.advancement()
                    .display(
                            ModItems.THE_FOOL_CARD,
                            Component.translatable("advancements.call_of_gaia.major_arcana.title"),
                            Component.translatable("advancements.call_of_gaia.major_arcana.description"),
                            ResourceLocation.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_any_card", InventoryChangeTrigger.TriggerInstance.hasItems(
                            cards[0].item().get(),
                            cards[1].item().get(),
                            cards[2].item().get(),
                            cards[3].item().get(),
                            cards[4].item().get(),
                            cards[5].item().get(),
                            cards[6].item().get(),
                            cards[7].item().get(),
                            cards[8].item().get(),
                            cards[9].item().get(),
                            cards[10].item().get(),
                            cards[11].item().get(),
                            cards[12].item().get(),
                            cards[13].item().get(),
                            cards[14].item().get(),
                            cards[15].item().get(),
                            cards[16].item().get(),
                            cards[17].item().get(),
                            cards[18].item().get(),
                            cards[19].item().get(),
                            cards[20].item().get(),
                            cards[21].item().get()))
                    .save(output, CallOfGaia.MOD_ID + ":major_arcana/root");

            for (CardAdvancement card : cards) {
                Advancement.Builder.advancement()
                        .parent(majorArcanaRoot)
                        .display(
                                card.item(),
                                Component.translatable("advancements.call_of_gaia." + card.path() + ".title"),
                                Component.translatable("advancements.call_of_gaia." + card.path() + ".description"),
                                null,
                                AdvancementType.CHALLENGE,
                                true,
                                true,
                                true
                        )
                        .addCriterion("has_card", InventoryChangeTrigger.TriggerInstance.hasItems(card.item().get()))
                        .save(output, CallOfGaia.MOD_ID + ":major_arcana/" + card.path());
            }
        }

        private record CardAdvancement(net.neoforged.neoforge.registries.DeferredItem<? extends Item> item, String path) {
        }
    }
}