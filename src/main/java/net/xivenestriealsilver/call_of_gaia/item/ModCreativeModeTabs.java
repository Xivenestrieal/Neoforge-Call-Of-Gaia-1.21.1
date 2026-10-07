package net.xivenestriealsilver.call_of_gaia.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CallOfGaia.MOD_ID);

    public static final Supplier<CreativeModeTab> LAZURITE_ITEMS_TAB = CREATIVE_MODE_TAB.register("lazurite_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LAZURITE_INGOT.get()))
                    .title(Component.translatable("creativetab.call_of_gaia.lazurite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.LAZURITE_INGOT);
                        output.accept(ModItems.LAZURITE_KEY);
                        output.accept(ModItems.RAW_LAZURITE);
                        output.accept(ModItems.HOGYOKU_SOUL);
                        output.accept(ModItems.SEEKING_EYES);
                        output.accept(ModItems.CORRUPTED_SOUL);
                        output.accept(ModItems.ROOT_HEART);
                        output.accept(ModItems.ENT_CHARCOAL);
                        output.accept(ModItems.FROZEN_FLAME);
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.GECKO_SPAWN_EGG);

                        output.accept(ModItems.LAZURITE_SWORD);
                        output.accept(ModItems.LAZURITE_PICKAXE);
                        output.accept(ModItems.LAZURITE_SHOVEL);
                        output.accept(ModItems.LAZURITE_AXE);
                        output.accept(ModItems.LAZURITE_HOE);

                        output.accept(ModItems.ICE_SPEAR);

                        output.accept(ModItems.LAZURITE_HELMET);
                        output.accept(ModItems.LAZURITE_CHESTPLATE);
                        output.accept(ModItems.LAZURITE_LEGGINGS);
                        output.accept(ModItems.LAZURITE_BOOTS);

                        output.accept(ModItems.THE_FOOL_CARD.get());
                        output.accept(ModItems.THE_MAGICIAN_CARD.get());
                        output.accept(ModItems.THE_HIGH_PRIESTESS_CARD.get());
                        output.accept(ModItems.THE_EMPRESS_CARD.get());
                        output.accept(ModItems.THE_EMPEROR_CARD.get());
                        output.accept(ModItems.THE_HIEROPHANT_CARD.get());
                        output.accept(ModItems.THE_LOVERS_CARD.get());
                        output.accept(ModItems.THE_CHARIOT_CARD.get());
                        output.accept(ModItems.STRENGTH_CARD.get());
                        output.accept(ModItems.THE_HERMIT_CARD.get());
                        output.accept(ModItems.WHEEL_OF_FORTUNE_CARD.get());
                        output.accept(ModItems.JUSTICE_CARD.get());
                        output.accept(ModItems.THE_HANGED_MAN_CARD.get());
                        output.accept(ModItems.DEATH_CARD.get());
                        output.accept(ModItems.TEMPERANCE_CARD.get());
                        output.accept(ModItems.THE_DEVIL_CARD.get());
                        output.accept(ModItems.THE_TOWER_CARD.get());
                        output.accept(ModItems.THE_STAR_CARD.get());
                        output.accept(ModItems.THE_MOON_CARD.get());
                        output.accept(ModItems.THE_SUN_CARD.get());
                        output.accept(ModItems.JUDGEMENT_CARD.get());
                        output.accept(ModItems.THE_WORLD_CARD.get());


                      //  output.accept(ModItems.GUIDE_BOOK);

                        // Soul Evolutions
                        output.accept(ModItems.PURE_EVOLUTION_AWAKENING);
                        output.accept(ModItems.CORRUPT_EVOLUTION_AWAKENING);
                        output.accept(ModItems.TAINTED_EVOLUTION_AWAKENING);
                    }).build());

    public static final Supplier<CreativeModeTab> LAZURITE_BLOCK_TAB = CREATIVE_MODE_TAB.register("lazurite_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LAZURITE_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CallOfGaia.MOD_ID, "lazurite_items_tab"))
                    .title(Component.translatable("creativetab.call_of_gaia.lazurite_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.LAZURITE_BLOCK);
                        output.accept(ModBlocks.LAZURITE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_LAZURITE_ORE);
                        output.accept(ModBlocks.SEEKING_BLOCK);
                        output.accept(ModBlocks.LAZURITE_LAMP);

                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
