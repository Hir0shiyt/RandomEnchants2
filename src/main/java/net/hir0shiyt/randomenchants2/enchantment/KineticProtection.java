package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.hir0shiyt.randomenchants2.util.EnchantUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class KineticProtection extends Enchantment {
    public KineticProtection(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 30;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.kineticProtectionConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ModConfig.ServerConfig.kineticProtectionConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.kineticProtectionConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.kineticProtectionConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return !(enchantment instanceof ProtectionEnchantment);
    }

    @SubscribeEvent
    public static void elytraDamage(LivingDamageEvent e) {
        if (e.getEntity() instanceof Player player) {
            ItemStack elytra = player.getItemBySlot(EquipmentSlot.CHEST);
            if (EnchantUtils.hasEnch(elytra, ModEnchantments.KINETIC_PROTECTION.get())) {

                if (e.getEntity().isFallFlying()) {
                    int enchLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.KINETIC_PROTECTION.get(), elytra);
                    int dmg = (int) e.getAmount();
                    int negDmg = (int) Math.max(0, dmg - enchLevel * 1.2);

                    e.setAmount(negDmg);
                }
            }
        }
    }
}
