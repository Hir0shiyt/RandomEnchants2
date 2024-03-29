package net.hir0shiyt.randomenchants2.curse;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.hir0shiyt.randomenchants2.enchantment.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class BreakingCurse extends Enchantment {
    public BreakingCurse(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int level) {
        return  25;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.breakingConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.breakingConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @SubscribeEvent
    public static void amplifyDamage(BlockEvent.BreakEvent e) {
        Player p = e.getPlayer();
        ItemStack heldItem = p.getMainHandItem();
        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BREAKING_CURSE, heldItem);
        heldItem.hurtAndBreak(level, p, player -> player.broadcastBreakEvent(p.getUsedItemHand()));
    }
}
