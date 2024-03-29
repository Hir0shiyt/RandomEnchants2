package net.hir0shiyt.randomenchants2.curse;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class LingeringShadowsCurse extends Enchantment {
    public LingeringShadowsCurse(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
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
        return ModConfig.ServerConfig.lingeringShadowsConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.lingeringShadowsConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    public static boolean handled = false;

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (handled) {
            handled = false;
            return;
        }
        if (!(user instanceof Player)) return;
        int userHp = (int) user.getHealth();
        int r = userHp * 6;
        double x = user.getX();
        double y = user.getY();
        double z = user.getZ();
        List<Monster> aggro = target.level.getEntitiesOfClass(Monster.class, new AABB(x- r, y - r, z - r, x + r, y + r, z + r));
        for (Monster triggered : aggro) {
            triggered.teleportTo(x + 18, y, z + 5);
        }
        handled = true;
    }
}