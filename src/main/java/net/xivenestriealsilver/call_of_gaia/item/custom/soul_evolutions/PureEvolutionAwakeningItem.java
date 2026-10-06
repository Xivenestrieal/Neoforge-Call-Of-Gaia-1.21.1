package net.xivenestriealsilver.call_of_gaia.item.custom.soul_evolutions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class PureEvolutionAwakeningItem extends Item {

    // A basic food baseline so the game allows the player to eat it
    private static final FoodProperties BASE_FOOD = new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(20F)
            .alwaysEdible()
            .build();

    public PureEvolutionAwakeningItem(Properties properties) {
        super(properties.component(DataComponents.FOOD, BASE_FOOD));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // Run standard logic (handles consumption and shrinking the item stack)
        ItemStack resultStack = super.finishUsingItem(stack, level, entity);

        // Run calculations only on the server side to prevent desyncs
        if (!level.isClientSide()) {
            BlockPos pos = entity.blockPosition();
            
            // Call your reusable scanner! (Radius of 5 blocks around the player)
            int fireCount = BlockVicinityScanner.countMatchingBlocks(level, pos, 5, state -> 
                state.is(Blocks.FIRE) || state.is(Blocks.SOUL_FIRE) || state.getFluidState().isSourceOrFlowing()
            );

            // Dynamically assign buffs based on the fire count
            if (fireCount > 0 && fireCount < 40) {
                // Tier 1: No fire nearby (Disappointment/Mild buff)
                entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0));
                sendMessage(entity, "The apple feels cold. You need more fire... (0 blocks found)");
                
            } else if (fireCount >= 40 && fireCount < 95) {
                // Tier 2: A small amount of fire nearby
                entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0)); // 1 min
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 0));     // Strength 1
                sendMessage(entity, "The spark ignites within you! (" + fireCount + " fire blocks found)");
                
            } else {
                // Tier 3: Insane fire energy (10+ fire/lava blocks)
                entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0)); // 5 mins
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 4));    // Strength 5 for 1 min
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 2));     // Regen 3
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1));   // Speed 2
                
                sendMessage(entity, "INFERNAL POWER AWAKENED! (" + fireCount + " fire blocks found)");
            }
        }

        return resultStack;
    }

    // Quick helper to send chat feedback to the player
    private void sendMessage(LivingEntity entity, String text) {
        if (entity instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal(text));
        }
    }
}
