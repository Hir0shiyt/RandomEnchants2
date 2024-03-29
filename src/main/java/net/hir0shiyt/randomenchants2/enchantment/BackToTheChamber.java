package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class BackToTheChamber extends Enchantment {
    public BackToTheChamber(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
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
        return ModConfig.ServerConfig.backToTheChamberConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
            return ModConfig.ServerConfig.backToTheChamberConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.backToTheChamberConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.backToTheChamberConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return !(enchantment instanceof ArrowInfiniteEnchantment) &&
                !(enchantment instanceof MultiShotEnchantment) &&
                super.checkCompatibility(enchantment);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onArrowHit(ProjectileImpactEvent event) {
        if (event.getRayTraceResult() instanceof EntityHitResult entityHit) {
            Entity targetEntity = entityHit.getEntity();
            Entity arrowEntity = event.getEntity();
            if (arrowEntity instanceof AbstractArrow) {
                AbstractArrow arrow = (AbstractArrow) arrowEntity;
                Entity shooter = arrow.getOwner();
                if (shooter instanceof Player) {
                    Player playerShooter = (Player) shooter;
                    ItemStack heldItem = playerShooter.getMainHandItem();
                    if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BACK_TO_THE_CHAMBER, heldItem) > 0) {
                        ItemStack arrowStack = new ItemStack(Items.ARROW, 1);
                        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BACK_TO_THE_CHAMBER, heldItem);
                        if (!arrowStack.isEmpty() && arrowStack.getItem() instanceof ArrowItem) {
                            double chance = 0.2 * level;
                            if (arrow.level instanceof ServerLevel serverLevel && serverLevel.getRandom().nextDouble() < chance) {
                                if (playerShooter.getInventory().add(arrowStack)) {
                                    arrow.level.addFreshEntity(new ItemEntity(arrow.level, arrow.getX(), arrow.getY(), arrow.getZ(), arrowStack));
                                }
                            }
                        }
                        arrow.remove(Entity.RemovalReason.DISCARDED);
                    }
                }
            }
        }
    }
}
