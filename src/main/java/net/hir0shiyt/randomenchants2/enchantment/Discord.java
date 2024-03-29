package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.MultiShotEnchantment;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class Discord extends Enchantment {
    public Discord(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
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
        return ModConfig.ServerConfig.discordConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
            return ModConfig.ServerConfig.discordConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.discordConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.discordConfig.get() != ModConfig.Restriction.TREASURE;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return !(enchantment instanceof MultiShotEnchantment) &&
                super.checkCompatibility(enchantment);
    }

    private static boolean handled = false;

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (handled) {
            handled = false;
            return;
        }
        if (!(target instanceof LivingEntity)) return;
        int r = 64;
        double x = target.getX();
        double y = target.getY();
        double z = target.getZ();
        List<Entity> aggro = target.level.getEntitiesOfClass(Entity.class, new AABB(x- r, y - r, z - r, x + r, y + r, z + r));
        for (Entity triggered : aggro) {
            if (triggered instanceof LivingEntity) {
                ((LivingEntity) triggered).setLastHurtByMob((LivingEntity) target);
            }
        }
        handled = true;
    }
}