package net.xivenestriealsilver.soulsmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
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
            new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.LAZURITE, 5f, -2.4f))));

    public static final DeferredItem<PickaxeItem> LAZURITE_PICKAXE = ITEMS.register("lazurite_pickaxe", ()-> new PickaxeItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.LAZURITE, 1.0f, -2.8f))));

    public static final DeferredItem<ShovelItem> LAZURITE_SHOVEL = ITEMS.register("lazurite_shovel", ()-> new ShovelItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.LAZURITE, 1.5f, -3.0f))));

    public static final DeferredItem<AxeItem> LAZURITE_AXE = ITEMS.register("lazurite_axe", ()-> new AxeItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.LAZURITE, 6.0f, -3.2f))));

    public static final DeferredItem<HoeItem> LAZURITE_HOE = ITEMS.register("lazurite_hoe", ()-> new HoeItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.LAZURITE, 0f, -3.0f))));





    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
