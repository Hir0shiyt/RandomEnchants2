package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.hir0shiyt.randomenchants2.util.EnchantUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class Swift extends Enchantment {
    public Swift(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category ,slots);
    }

    @Override
    public int getMinCost(int level) {
        return 15 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.swiftConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
            return ModConfig.ServerConfig.swiftConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.swiftConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.swiftConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!EnchantUtils.hasEnch(player, ModEnchantments.SWIFT.get())) return;
        int swing = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "f_20922_");
        swing += EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SWIFT.get(), player);
        ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, player, swing, "f_20922_");
    }
}
