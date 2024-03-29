package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class InstantDeath extends Enchantment {
    public InstantDeath(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
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
        return ModConfig.ServerConfig.instantDeathConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ModConfig.ServerConfig.instantDeathConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.instantDeathConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.instantDeathConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @SubscribeEvent
    public static void onArrowImpact(ProjectileImpactEvent event) {
        Entity arrow = event.getEntity();
        if (arrow instanceof ThrowableProjectile || arrow instanceof Fireball || arrow instanceof DragonFireball || arrow instanceof ThrownTrident || arrow instanceof FireworkRocketEntity || arrow instanceof FishingHook) {return;}
        if (arrow instanceof AbstractArrow) {
            LivingEntity shooter = (LivingEntity) ((AbstractArrow) arrow).getOwner();
            if (shooter instanceof Player) {
                Player player = (Player) shooter;
                if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.INSTANT_DEATH, player.getMainHandItem()) > 0) {
                    if (event.getRayTraceResult().getType() == HitResult.Type.ENTITY) {
                        Entity target = ((EntityHitResult) event.getRayTraceResult()).getEntity();
                        if (target instanceof LivingEntity) {
                            LivingEntity livingTarget = (LivingEntity) target;
                            livingTarget.setHealth(0);
                            arrow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent event) {
        Entity target = event.getTarget();
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity) target;
            Entity attacker = event.getEntity();
            if (attacker instanceof Player) {
                Player player = (Player) attacker;
                ItemStack heldItem = player.getMainHandItem();
                if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.INSTANT_DEATH, heldItem) > 0) {
                    livingTarget.setHealth(0);
                }
            }
        }
    }
}