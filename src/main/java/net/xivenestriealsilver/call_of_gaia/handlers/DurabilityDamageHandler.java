package net.xivenestriealsilver.call_of_gaia.handlers;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class DurabilityDamageHandler {

    private static final List<BiFunction<Player, Integer, Integer>> DAMAGE_MODIFIERS = new ArrayList<>();

    public static void penalizeAttackerDurability(Player player, int baseDamage) {
        ItemStack weapon = player.getMainHandItem();

        if (!weapon.isEmpty() && weapon.isDamageableItem()) {
            if (player.level() instanceof ServerLevel serverLevel) {
                
                int finalDamage = baseDamage;
                for (BiFunction<Player, Integer, Integer> modifier : DAMAGE_MODIFIERS) {
                    finalDamage = modifier.apply(player, finalDamage);
                }

                weapon.hurtAndBreak(finalDamage, serverLevel, player, (item) -> {
                    player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                });
            }
        }
    }

    public static void registerModifier(BiFunction<Player, Integer, Integer> modifier) {
        DAMAGE_MODIFIERS.clear();
        DAMAGE_MODIFIERS.add(modifier);
    }
}
