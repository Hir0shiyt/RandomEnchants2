package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.hir0shiyt.randomenchants2.util.EnchantUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class DimensionalShuffle extends Enchantment {
    private static final Map<ItemStack, Integer> itemCooldown = new HashMap<>();
    private static final Random RANDOM = new Random();
    public DimensionalShuffle(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 15 + level * 2;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.dimensionalShuffleConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ModConfig.ServerConfig.dimensionalShuffleConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.dimensionalShuffleConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.dimensionalShuffleConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            ItemStack armor = player.getItemBySlot(EquipmentSlot.CHEST);
            if (EnchantUtils.hasEnch(armor, ModEnchantments.DIMENSIONAL_SHUFFLE.get())) {
                int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DIMENSIONAL_SHUFFLE.get(), armor);
                if (!itemCooldown.containsKey(armor) || itemCooldown.get(armor) <= 0) {
                    if (level > 0 && player.getHealth() - event.getAmount() <= 0) {
                        event.setCanceled(true);

                        teleportPlayerRandomly(player, level);
                        int cooldown = 320 - level * level;

                        itemCooldown.put(armor, cooldown);
                    }
                }
            }
        }
    }

    private static void teleportPlayerRandomly(Player player, int level) {
        Level world = player.level;
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        double radius = level * 10;
        BlockPos targetPos = null;

        for (int i = 0; i < 10; i++) {
            double newX = x + (RANDOM.nextDouble() - 0.5) * radius;
            double newY = y + (RANDOM.nextDouble() - 0.5) * radius;
            double newZ = z + (RANDOM.nextDouble() - 0.5) * radius;

            BlockPos candidatePos = new BlockPos((int) newX, (int) newY, (int) newZ);

            if (!world.isLoaded(candidatePos)) {
                continue;
            }

            BlockState candidateBlock = world.getBlockState(candidatePos);

            while (candidateBlock.isAir()) {
                candidatePos = candidatePos.below();
                candidateBlock = world.getBlockState(candidatePos);
            }

            if (!world.containsAnyLiquid(player.getBoundingBoxForCulling())) {
                targetPos = candidatePos;
                break;
            }
        }

        if (targetPos != null) {
            double adjustedX = targetPos.getX() + 0.5;
            double adjustedY = targetPos.getY() + 2.0;
            double adjustedZ = targetPos.getZ() + 0.5;

            player.teleportTo(adjustedX, adjustedY, adjustedZ);
        }
    }
}
