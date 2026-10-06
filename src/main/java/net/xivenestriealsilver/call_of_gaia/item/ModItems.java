package net.xivenestriealsilver.call_of_gaia.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.entity.ModEntities;
import net.xivenestriealsilver.call_of_gaia.item.custom.*;
import net.xivenestriealsilver.call_of_gaia.item.custom.soul_evolutions.PureEvolutionAwakeningItem;
import net.xivenestriealsilver.call_of_gaia.item.custom.tarot_cards.*;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CallOfGaia.MOD_ID);

                                                    // MATERIALS
    public static final DeferredItem<Item> RAW_LAZURITE = ITEMS.register("raw_lazurite",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LAZURITE_INGOT = ITEMS.register("lazurite_ingot",
            () -> new Item(new Item.Properties()));


                                                    //BOSS ITEMS
    public static final DeferredItem<Item> CORRUPTED_SOUL = ITEMS.register("corrupted_soul",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LAZURITE_KEY = ITEMS.register("lazurite_key",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SEEKING_EYES = ITEMS.register("seeking_eyes",
            () -> new Item(new Item.Properties())  {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                  tooltipComponents.add(Component.translatable("item.call_of_gaia.seeking_eyes.tooltip"));

                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


                                            //SOUL EVOLUTION
    public static final DeferredItem<Item> HOGYOKU_SOUL = ITEMS.register("hogyoku_soul",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PURE_EVOLUTION_AWAKENING = ITEMS.register("pure_evolution_awakening",
            () -> new PureEvolutionAwakeningItem(new Item.Properties().food(ModFoodProperties.PURE_EVOLUTION_AWAKENING).rarity(Rarity.EPIC).stacksTo(1)));


                                            //FOOD / CONSUMABLES
    public static final DeferredItem<Item> ROOT_HEART = ITEMS.register("root_heart",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROOT_HEART)));

    public static final DeferredItem<Item> ENT_CHARCOAL = ITEMS.register("ent_charcoal",
            () -> new FuelItem(new Item.Properties(), 2000));

    public static final DeferredItem<Item> FROZEN_FLAME = ITEMS.register("frozen_flame",
            () -> new Item(new Item.Properties()));


                                                    // MAJOR ARCANA
    public static final DeferredItem<Item> THE_FOOL_CARD = ITEMS.register("the_fool_card",
            () -> new TheFoolCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_MAGICIAN_CARD = ITEMS.register("the_magician_card",
            () -> new TheMagicianCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_HIGH_PRIESTESS_CARD = ITEMS.register("the_high_priestess_card",
            () -> new TheHighPriestessCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_EMPRESS_CARD = ITEMS.register("the_empress_card",
            () -> new TheEmpressCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_EMPEROR_CARD = ITEMS.register("the_emperor_card",
            () -> new TheEmperorCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_HIEROPHANT_CARD = ITEMS.register("the_hierophant_card",
            () -> new TheHierophantCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_LOVERS_CARD = ITEMS.register("the_lovers_card",
            () -> new TheLoversCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_CHARIOT_CARD = ITEMS.register("the_chariot_card",
            () -> new TheFoolCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> STRENGTH_CARD = ITEMS.register("strength_card",
            () -> new StrengthCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_HERMIT_CARD = ITEMS.register("the_hermit_card",
            () -> new TheHermitCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> WHEEL_OF_FORTUNE_CARD = ITEMS.register("wheel_of_fortune_card",
            () -> new WheelOfFortuneCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> JUSTICE_CARD = ITEMS.register("justice_card",
            () -> new JusticeCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_HANGED_MAN_CARD = ITEMS.register("the_hanged_man_card",
            () -> new TheHangedManCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> DEATH_CARD = ITEMS.register("death_card",
            () -> new DeathCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> TEMPERANCE_CARD = ITEMS.register("temperance_card",
            () -> new TemperanceCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_DEVIL_CARD = ITEMS.register("the_devil_card",
            () -> new TheDevilCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_TOWER_CARD = ITEMS.register("the_tower_card",
            () -> new TheTowerCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_STAR_CARD = ITEMS.register("the_star_card",
            () -> new TheStarCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_MOON_CARD = ITEMS.register("the_moon_card",
            () -> new TheMoonCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_SUN_CARD = ITEMS.register("the_sun_card",
            () -> new TheSunCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> JUDGEMENT_CARD = ITEMS.register("judgement_card",
            () -> new JudgementCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final DeferredItem<Item> THE_WORLD_CARD = ITEMS.register("the_world_card",
            () -> new TheWorldCardItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));


                                                                //EQUIPMENT
    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(50)));

    public static final DeferredItem<SwordItem> LAZURITE_SWORD = ITEMS.register("lazurite_sword", ()-> new SwordItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.LAZURITE, 3f, -2.4f))));

    public static final DeferredItem<PickaxeItem> LAZURITE_PICKAXE = ITEMS.register("lazurite_pickaxe", ()-> new PickaxeItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.LAZURITE, 1.0f, -2.8f))));

    public static final DeferredItem<ShovelItem> LAZURITE_SHOVEL = ITEMS.register("lazurite_shovel", ()-> new ShovelItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.LAZURITE, 1.5f, -3.0f))));

    public static final DeferredItem<AxeItem> LAZURITE_AXE = ITEMS.register("lazurite_axe", ()-> new AxeItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.LAZURITE, 5.0f, -3.0f))));

    public static final DeferredItem<HoeItem> LAZURITE_HOE = ITEMS.register("lazurite_hoe", ()-> new HoeItem(ModToolTiers.LAZURITE,
            new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.LAZURITE, -4.0f, 0f))));


                                                        //ARMOR
    public static final DeferredItem<ArmorItem> LAZURITE_HELMET = ITEMS.register("lazurite_helmet", ()->
            new ModArmorItem(ModArmorMaterials.LAZURITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(30))));

    public static final DeferredItem<ArmorItem> LAZURITE_CHESTPLATE = ITEMS.register("lazurite_chestplate", ()->
            new ArmorItem(ModArmorMaterials.LAZURITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(30))));

    public static final DeferredItem<ArmorItem> LAZURITE_LEGGINGS = ITEMS.register("lazurite_leggings", ()->
            new ArmorItem(ModArmorMaterials.LAZURITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(30))));

    public static final DeferredItem<ArmorItem> LAZURITE_BOOTS = ITEMS.register("lazurite_boots", ()->
            new ArmorItem(ModArmorMaterials.LAZURITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(30))));


                                                //ENTITY-RELATED
    public static final DeferredItem<Item> GECKO_SPAWN_EGG = ITEMS.register("gecko_spawn_egg",
            ()-> new DeferredSpawnEggItem(ModEntities.GECKO, 0x31afaf, 0xffac00,
                    new Item.Properties()));


                                                 //PROJECTILES
    public static final DeferredItem<Item> ICE_SPEAR = ITEMS.register("ice_spear",
            ()-> new IceSpearItem(new Item.Properties().stacksTo(4)));





    //public static final DeferredItem<Item> GUIDE_BOOK = ITEMS.register("guide_book",
          //  () -> new GuideBookItem(new Item.Properties()));










    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
