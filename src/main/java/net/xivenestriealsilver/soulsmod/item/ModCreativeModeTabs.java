package net.xivenestriealsilver.soulsmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xivenestriealsilver.soulsmod.SoulsMod;
import net.xivenestriealsilver.soulsmod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SoulsMod.MOD_ID);

    public static final Supplier<CreativeModeTab> LAZURITE_ITEMS_TAB = CREATIVE_MODE_TAB.register("lazurite_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LAZURITE_INGOT.get()))
                    .title(Component.translatable("creativetab.soulsmod.lazurite_items"))
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

                        output.accept(ModItems.LAZURITE_SWORD);
                        output.accept(ModItems.LAZURITE_PICKAXE);
                        output.accept(ModItems.LAZURITE_SHOVEL);
                        output.accept(ModItems.LAZURITE_AXE);
                        output.accept(ModItems.LAZURITE_HOE);
                    }).build());

    public static final Supplier<CreativeModeTab> LAZURITE_BLOCK_TAB = CREATIVE_MODE_TAB.register("lazurite_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LAZURITE_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SoulsMod.MOD_ID, "lazurite_items_tab"))
                    .title(Component.translatable("creativetab.soulsmod.lazurite_blocks"))
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
