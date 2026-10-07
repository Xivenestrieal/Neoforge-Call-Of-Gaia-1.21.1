package net.xivenestriealsilver.call_of_gaia.item.custom.soul_evolutions;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.xivenestriealsilver.call_of_gaia.handlers.VicinityScannerHandler;

public class CorruptEvolutionAwakeningItem extends Item {

    public CorruptEvolutionAwakeningItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack resultStack = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide()) {
            BlockPos pos = entity.blockPosition();

            // CONFIGURE THE HANDLER ON THE FLY
            VicinityScannerHandler.ScanResult result = new VicinityScannerHandler.Request(level, pos)
                    .withRadius(4) // Dynamic range configuration
                    .filterBlocks(
                            state -> state.is(Blocks.FIRE) || state.is(Blocks.SOUL_FIRE) || state.is(Blocks.LAVA),
                            10 // CAP: Heat blocks can only ever provide a max value of 10
                    )
                    .filterEntities(targetEntity ->
                            // Condition matching flying fire projectiles, fireballs, or dropped spell books
                            targetEntity.getType().getDescriptionId().contains("fireball") ||
                                    targetEntity.getType().getDescriptionId().contains("spell")
                    )
                    .execute();


            int finalPower = result.getTotalPower();

            if (finalPower < 40) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1));
                sendMessage(entity, "Tier 1: " + finalPower + " (" + result.cappedBlockEnergy() + " from ground, " + result.entityEnergy() + " from active spells!)");
            } else if (finalPower < 95) {
                sendMessage(entity, "Tier 2: " + finalPower + " (" + result.cappedBlockEnergy() + " from ground, " + result.entityEnergy() + " from active spells!)");
            } else {
                sendMessage(entity, "Tier 3: " + finalPower + " (" + result.cappedBlockEnergy() + " from ground, " + result.entityEnergy() + " from active spells!)");
            }
        }

        return resultStack;
    }

    private void sendMessage(LivingEntity entity, String text) {
        if (entity instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal(text));
        }
    }
}
