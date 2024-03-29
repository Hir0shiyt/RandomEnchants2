package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.hir0shiyt.randomenchants2.util.EnchantUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ZenSanctuary extends Enchantment {
    public ZenSanctuary(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 30;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.zenSanctuaryConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ModConfig.ServerConfig.zenSanctuaryConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.zenSanctuaryConfig.get() == ModConfig.Restriction.ENABLED;
    }

    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.zenSanctuaryConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @SubscribeEvent
    public static void targetEvent(LivingChangeTargetEvent event) {
        if (event.getNewTarget() instanceof Player player) {
            if (EnchantUtils.hasEnch(player.getItemBySlot(EquipmentSlot.CHEST), ModEnchantments.ZEN_SANCTUARY.get())) {
                event.setNewTarget(null);
                event.setCanceled(true);
            }
        }
    }
}
