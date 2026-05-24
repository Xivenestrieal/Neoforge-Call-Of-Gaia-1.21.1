package net.xivenestriealsilver.call_of_gaia.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;



public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS=
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, CallOfGaia.MOD_ID);


    public static final Holder<MobEffect> BLEED_EFFECT = MOB_EFFECTS.register("bleed",
            ()-> new BleedEffect(MobEffectCategory.HARMFUL, 0xDC143C));

    public static final Holder<MobEffect> FROSTBITE_EFFECT = MOB_EFFECTS.register("frostbite",
            () -> new FrostbiteEffect(MobEffectCategory.HARMFUL, 0x00BFFF));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
