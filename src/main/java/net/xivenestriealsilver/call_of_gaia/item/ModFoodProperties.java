package net.xivenestriealsilver.call_of_gaia.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public  static  final FoodProperties ROOT_HEART = new FoodProperties.Builder().nutrition(6).saturationModifier(10f)
            .effect(()-> new MobEffectInstance(MobEffects.REGENERATION, 100), 1f).build();

    public static final FoodProperties PURE_EVOLUTION_AWAKENING = new FoodProperties.Builder().nutrition(0).saturationModifier(0f)
            .effect(()-> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600), 1f).build();

    public static final FoodProperties CORRUPT_EVOLUTION_AWAKENING = new FoodProperties.Builder().nutrition(0).saturationModifier(0f)
            .effect(()-> new MobEffectInstance(MobEffects.WEAKNESS, 600), 1f).build();

    public static final FoodProperties TAINTED_EVOLUTION_AWAKENING = new FoodProperties.Builder().nutrition(0).saturationModifier(0f)
            .effect(()-> new MobEffectInstance(MobEffects.POISON, 600), 1f).build();

}
