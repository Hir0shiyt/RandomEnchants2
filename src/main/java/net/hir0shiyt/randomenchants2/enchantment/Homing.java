package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.hir0shiyt.randomenchants2.util.EnchantUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.MultiShotEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class Homing extends Enchantment {
    public Homing(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public int getMaxLevel() {
        return super.getMaxLevel();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.homingConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
            return ModConfig.ServerConfig.homingConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.homingConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.homingConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return !(enchantment instanceof MultiShotEnchantment) &&
                !(enchantment instanceof Torches) &&
                !(enchantment instanceof Transposition) &&
                !(enchantment instanceof Teleportation) &&
                !(enchantment instanceof TrueShot) &&
                super.checkCompatibility(enchantment);
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof AbstractArrow && ((AbstractArrow) entity).getOwner() instanceof Player player) {
            ItemStack heldItem = player.getMainHandItem();
            if (EnchantUtils.hasEnch(heldItem, ModEnchantments.HOMING)) {
                AbstractArrow arrow = (AbstractArrow) entity;

                double arrowVelocity = arrow.getDeltaMovement().length();

                Level level = arrow.level;
                Vec3 shooterPos = new Vec3(player.getX(), player.getY() + player.getEyeHeight(player.getPose()), player.getZ());
                List<LivingEntity> livingEntities = getLivingEntitiesInWorld(level, arrow.getOwner());
                LivingEntity nearestEntity = null;
                double nearestDistanceSq = Double.MAX_VALUE;
                for (LivingEntity livingEntity : livingEntities) {
                    double distanceSq = livingEntity.distanceToSqr(shooterPos);
                    if (distanceSq < nearestDistanceSq) {
                        nearestEntity = livingEntity;
                        nearestDistanceSq = distanceSq;
                    }
                }

                if (nearestEntity == null || nearestEntity instanceof Animal) {
                    return;
                }

                double targetY = nearestEntity.getY() + nearestEntity.getBbHeight() / 2.0;
                double yOffSet = nearestEntity.getBbHeight() < 0.5 ? nearestEntity.getBbHeight() : 0.5;
                Vec3 targetPos = new Vec3(nearestEntity.getX(), targetY - yOffSet, nearestEntity.getZ());
                Vec3 shootVec = targetPos.subtract(shooterPos).normalize().scale(arrowVelocity);
                arrow.setPos(shooterPos.x, shooterPos.y + arrow.getOwner().getEyeHeight(), shooterPos.z);
                arrow.setDeltaMovement(shootVec.x, shootVec.y, shootVec.z);
                double deltaX = targetPos.x - shooterPos.x;
                double deltaY = targetPos.y - shooterPos.y;
                double deltaZ = targetPos.z - shooterPos.z;
                double horizontalDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                float yaw = (float) (Math.atan2(deltaZ, deltaX) * (180.0 / Math.PI)) - 90.0F;
                float pitch = (float) -(Math.atan2(deltaY, horizontalDistance) * (180.0 / Math.PI));
                arrow.setYRot(yaw);
                arrow.setXRot(pitch);
                arrow.getPersistentData().putBoolean("randomenchants2:homing", true);
            }
        }
    }

    private static List<LivingEntity> getLivingEntitiesInWorld(Level level, Entity shooter) {
        AABB boundingBox = shooter.getBoundingBox().inflate(22.0);
        return level.getEntitiesOfClass(LivingEntity.class, boundingBox, entity -> entity != shooter);
    }
}