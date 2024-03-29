package net.hir0shiyt.randomenchants2.util;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.hir0shiyt.randomenchants2.enchantment.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onTooltip(ItemTooltipEvent event) {
        Player player = event.getEntity();
        if (player == null) return;
        List<Component> tooltip = event.getToolTip();
        if ((EnchantUtils.hasEnch(event.getItemStack(), ModEnchantments.STONE_BOUND.get()))) {
            ItemStack stack = player.getMainHandItem();
            if (event.getItemStack() != stack) return;
            tooltip.add(Component.literal("Mining Bonus: " + ChatFormatting.GREEN + "+" + stack.getDamageValue() * .02));
            tooltip.add(Component.literal("Damage Penalty: " + ChatFormatting.RED + "-" + stack.getDamageValue() * .02));
            if (event.getItemStack().getItem() instanceof EnchantedBookItem) {
                ListTag nbtTagList = EnchantedBookItem.getEnchantments(event.getItemStack());
                for (int i = 0; i < nbtTagList.size(); ++i) {
                }
            }
        }

        else if ((EnchantUtils.hasEnch(player, ModEnchantments.DIMENSIONAL_SHUFFLE.get()))) {
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            if (event.getItemStack() != chest) return;
            tooltip.add(Component.literal("Teleportation Range: " + ChatFormatting.AQUA + EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DIMENSIONAL_SHUFFLE.get(), chest) * 10 + " blocks"));
            tooltip.add(Component.literal("Shuffle Cooldown: " + ChatFormatting.GOLD + (16 - EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DIMENSIONAL_SHUFFLE.get(), chest) * EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DIMENSIONAL_SHUFFLE.get(), chest)) + " seconds"));
        }

        else if (EnchantUtils.hasEnch(player, ModEnchantments.ETHEREAL_EMBRACE.get())) {
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            double eLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.ETHEREAL_EMBRACE.get(), chest) * 0.05;
            if (event.getItemStack() != chest) return;
            tooltip.add(Component.literal("Chance of phasing through attacks: " + ChatFormatting.BLUE + eLevel));
        }

        else if (EnchantUtils.hasEnch(player, ModEnchantments.EXPLODING.get())) {
            ItemStack heldItem = player.getMainHandItem();
            float explosionSize = 1.0f + (float) EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.EXPLODING.get(), heldItem);
            if (event.getItemStack() != heldItem) return;
            tooltip.add(Component.literal("Explosion Size: " + ChatFormatting.DARK_RED + explosionSize));
        }

        else if (EnchantUtils.hasEnch(player, ModEnchantments.DUNGEONEERING.get())) {
            ItemStack heldItem = player.getMainHandItem();
            if (event.getItemStack() != heldItem) return;
            if (ModConfig.ServerConfig.dungeoneeringLootTableConfig.get() == ModConfig.Restriction2.ENABLED) {
                tooltip.add(Component.literal(ChatFormatting.DARK_PURPLE + "Getting loot from 4 different dungeons"));
            } else if (ModConfig.ServerConfig.dungeoneeringLootTableConfig.get() == ModConfig.Restriction2.DISABLED) {
                tooltip.add(Component.literal(ChatFormatting.DARK_AQUA + "Getting loot from 1 dungeon"));
            }
        }
    }
}
