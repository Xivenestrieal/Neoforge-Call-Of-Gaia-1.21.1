package net.xivenestriealsilver.call_of_gaia.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.xivenestriealsilver.call_of_gaia.entity.custom.zaara.ZaaraEntity;

public interface ZaaraAttack {
    void onStart(ZaaraEntity zaara, LivingEntity target);
    void tick(ZaaraEntity zaara, LivingEntity target);
    boolean isFinished();
}