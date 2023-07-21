package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class SolarEnchant extends Enchantment {
    private static final int REPAIR_COOLDOWN = 20;

    public SolarEnchant(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 5 + (level - 1) * 10;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @SubscribeEvent
    public static void applySolarEnchant(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START || event.player.level.isClientSide())
            return;

        if (event.player.tickCount % REPAIR_COOLDOWN == 0) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack stack = event.player.getItemBySlot(slot);
                if (!stack.isEmpty() && stack.isDamaged()) {
                    int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SOLAR_ENCHANT.get(), stack);
                    if (level > 0 && event.player.level.getBrightness(LightLayer.BLOCK, event.player.blockPosition()) >= 13) {
                        int repairAmount = level * 2;
                        stack.setDamageValue(Math.max(0, stack.getDamageValue() - repairAmount));
                    }
                }
            }
        }
    }
}

