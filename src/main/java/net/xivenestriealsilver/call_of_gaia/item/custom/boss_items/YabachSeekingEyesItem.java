package net.xivenestriealsilver.call_of_gaia.item.custom.boss_items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class YabachSeekingEyesItem extends Item{
    public YabachSeekingEyesItem(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
            tooltipComponents.add(Component.translatable("tooltip.call_of_gaia.yabach_seeking_eyes"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
            player.startUsingItem(hand);
            if (level instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel)level;
                BlockPos blockpos = serverlevel.findNearestMapStructure(StructureTags.EYE_OF_ENDER_LOCATED, player.blockPosition(), 100, false);
                if (blockpos != null) {
                    EyeOfEnder eyeofender = new EyeOfEnder(level, player.getX(), player.getY((double)0.5F), player.getZ());
                    eyeofender.setItem(itemstack);
                    eyeofender.signalTo(blockpos);
                    level.gameEvent(GameEvent.PROJECTILE_SHOOT, eyeofender.position(), GameEvent.Context.of(player));
                    level.addFreshEntity(eyeofender);
                    if (player instanceof ServerPlayer) {
                        ServerPlayer serverplayer = (ServerPlayer)player;
                        CriteriaTriggers.USED_ENDER_EYE.trigger(serverplayer, blockpos);
                    }

                    float f = Mth.lerp(level.random.nextFloat(), 0.33F, 0.5F);
                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_EYE_LAUNCH, SoundSource.NEUTRAL, 1.0F, f);
                    itemstack.consume(1, player);
                    player.awardStat(Stats.ITEM_USED.get(this));
                    player.swing(hand, true);
                    return InteractionResultHolder.success(itemstack);
                }
            }

            return InteractionResultHolder.consume(itemstack);
        }
    }

