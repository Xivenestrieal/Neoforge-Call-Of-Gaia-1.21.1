package net.xivenestriealsilver.call_of_gaia.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public  static  final FoodProperties ROOT_HEART = new FoodProperties.Builder().nutrition(6).saturationModifier(10f)
            .effect(()-> new MobEffectInstance(MobEffects.REGENERATION, 100), 1f).build();

}
