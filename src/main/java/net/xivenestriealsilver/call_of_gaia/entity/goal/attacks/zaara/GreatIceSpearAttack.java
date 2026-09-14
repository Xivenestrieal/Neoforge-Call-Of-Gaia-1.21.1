package net.xivenestriealsilver.call_of_gaia.entity.goal.attacks.zaara;

import net.minecraft.world.entity.LivingEntity;
import net.xivenestriealsilver.call_of_gaia.entity.custom.IceSpearProjectileEntity;
import net.xivenestriealsilver.call_of_gaia.entity.custom.zaara.ZaaraEntity;
import net.xivenestriealsilver.call_of_gaia.entity.goal.ZaaraAttack;
import net.xivenestriealsilver.call_of_gaia.entity.ModEntities; // Ensure this matches your package

public class GreatIceSpearAttack implements ZaaraAttack {
    private int timer = 0;
    private final int windupDuration = 60; // 3 seconds
    private boolean fired = false;

    @Override
    public void onStart(ZaaraEntity zaara, LivingEntity target) {
        zaara.startGreatSpearWindup();
    }

    @Override
    public void tick(ZaaraEntity zaara, LivingEntity target) {
        timer++;

        if (timer >= windupDuration && !fired) {
            spawnProjectile(zaara, target);
            fired = true;
        }
    }

    private void spawnProjectile(ZaaraEntity zaara, LivingEntity target) {
        IceSpearProjectileEntity spear = new IceSpearProjectileEntity(ModEntities.ICE_SPEAR.get(), zaara.level());

        spear.setPos(zaara.getX(), zaara.getEyeY() - 0.2, zaara.getZ());
        spear.setOwner(zaara);
        spear.setBaseDamage(4.0);

        double dx = target.getX() - zaara.getX();
        double dy = target.getY(0.5) - spear.getY();
        double dz = target.getZ() - zaara.getZ();
        double horizontalDist = Math.sqrt(dx * dx + dz * dz);

        spear.shoot(dx, dy + (horizontalDist * 0.2), dz, 1.6f, 0.0f);
        zaara.level().addFreshEntity(spear);
    }

    @Override
    public boolean isFinished() {
        return timer >= windupDuration + 20; // Allow 1 second cooldown after firing
    }
}