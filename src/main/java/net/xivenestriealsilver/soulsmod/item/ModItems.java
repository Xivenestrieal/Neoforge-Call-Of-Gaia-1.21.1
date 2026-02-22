package net.xivenestriealsilver.soulsmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xivenestriealsilver.soulsmod.SoulsMod;
import net.xivenestriealsilver.soulsmod.item.custom.ChiselItem;
import net.xivenestriealsilver.soulsmod.item.custom.FuelItem;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SoulsMod.MOD_ID);

    public static final DeferredItem<Item> RAW_LAZURITE = ITEMS.register("raw_lazurite",
            () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> LAZURITE_INGOT = ITEMS.register("lazurite_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CORRUPTED_SOUL = ITEMS.register("corrupted_soul",
            () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> LAZURITE_KEY = ITEMS.register("lazurite_key",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SEEKING_EYES = ITEMS.register("seeking_eyes",
            () -> new Item(new Item.Properties())  {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                  tooltipComponents.add(Component.translatable("item.soulsmod.seeking_eyes.tooltip"));

                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> HOGYOKU_SOUL = ITEMS.register("hogyoku_soul",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ROOT_HEART = ITEMS.register("root_heart",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROOT_HEART)));

    public static final DeferredItem<Item> ENT_CHARCOAL = ITEMS.register("ent_charcoal",
            () -> new FuelItem(new Item.Properties(), 2000));

    public static final DeferredItem<Item> FROZEN_FLAME = ITEMS.register("frozen_flame",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(50)));



    public static final DeferredItem<SwordItem> LAZURITE_SWORD = ITEMS.register("lazurite_sword", ()-> new SwordItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.LAZURITE, 4f, 9f))));





    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
