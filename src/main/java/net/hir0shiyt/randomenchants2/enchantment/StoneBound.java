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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class StoneBound extends Enchantment {
    public StoneBound(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.stoneBoundConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ModConfig.ServerConfig.stoneBoundConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.stoneBoundConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.stoneBoundConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (EnchantUtils.hasEnch(player, ModEnchantments.STONE_BOUND)) {
                LivingEntity entity = event.getEntityLiving();
                ItemStack stack = player.getMainHandItem();
                float reduction = .02f * stack.getDamageValue();
                entity.heal(reduction);
            }
        }
    }

    @SubscribeEvent
    public void onBreakBlock(PlayerEvent.BreakSpeed event) {
        Player player = event.getPlayer();
        if ((EnchantmentHelper.getEnchantmentLevel(ModEnchantments.STONE_BOUND ,player) > 0)) {
            float oldSpeed = event.getOriginalSpeed();
            ItemStack stack = player.getMainHandItem();
            float increase = .02f * stack.getDamageValue();
            event.setNewSpeed(increase + oldSpeed);
        }
    }
}