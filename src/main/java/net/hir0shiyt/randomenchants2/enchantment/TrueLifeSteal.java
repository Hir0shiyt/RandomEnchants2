package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.util.EnchantUtils;
import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class TrueLifeSteal extends Enchantment {
    public TrueLifeSteal(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
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
        return ModConfig.ServerConfig.trueLifeStealConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ModConfig.ServerConfig.trueLifeStealConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.trueLifeStealConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.trueLifeStealConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player) {
            Player player = (Player) event.getSource().getDirectEntity();
            if (EnchantUtils.hasEnch(player, ModEnchantments.TRUE_LIFE_STEAL)) {
                LivingEntity entity = event.getEntityLiving();
                float damage = event.getAmount();
                entity.hurt(DamageSource.GENERIC, damage*1.5f);
                player.heal(damage);
            }
        }
    }

    @SubscribeEvent
    public static void onArrowHit(ProjectileImpactEvent event) {
        Entity arrowEntity = event.getEntity();
        if (arrowEntity instanceof AbstractArrow) {
            AbstractArrow arrow = (AbstractArrow) arrowEntity;
            Entity shooter = arrow.getOwner();
            if (shooter instanceof Player && EnchantUtils.hasEnch((Player) shooter, ModEnchantments.TRUE_LIFE_STEAL)) {
                HitResult hitEntity = event.getRayTraceResult();
                if (hitEntity != null) {
                    float damage = (float) arrow.getDeltaMovement().length();
                    Minecraft.getInstance().execute(() -> {
                        if (hitEntity.getType() == HitResult.Type.ENTITY) {
                            Entity targetEntity = ((EntityHitResult) hitEntity).getEntity();
                            if (targetEntity instanceof LivingEntity) {
                                ((LivingEntity) targetEntity).hurt(DamageSource.GENERIC, damage * 1.5f);
                                ((Player) shooter).heal(damage);
                            }
                        }
                    });
                }
            }
        }
    }
}
