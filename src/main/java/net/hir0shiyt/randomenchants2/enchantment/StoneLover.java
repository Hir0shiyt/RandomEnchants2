package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class StoneLover extends Enchantment {

    public StoneLover(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 15; // Minimum cost required for the enchantment to appear
    }

    @Override
    public int getMaxLevel() {
        return 1; // Maximum enchantment level
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.stoneLoverConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
            return ModConfig.ServerConfig.stoneLoverConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.stoneLoverConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.stoneLoverConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return !(enchantment instanceof SolarEnchant) &&
                super.checkCompatibility(enchantment);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player != null) {
            ItemStack mainHandStack = player.getMainHandItem();
            int mainHandLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.STONE_LOVER, mainHandStack);

            if (mainHandLevel > 0) {
                Block block = event.getState().getBlock();
                if (block == Blocks.STONE && player.getRandom().nextFloat() < 0.8f) {
                    int repairAmount = 2;
                    mainHandStack.setDamageValue(Math.max(0, mainHandStack.getDamageValue() - repairAmount));
                    mainHandStack.setPopTime(5);
                }
            }
        }
    }
}