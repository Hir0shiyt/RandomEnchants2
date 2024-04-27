package net.hir0shiyt.randomenchants2.curse;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.hir0shiyt.randomenchants2.enchantment.ModEnchantments;
import net.hir0shiyt.randomenchants2.util.EnchantUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class DarkKingdomCurse extends Enchantment {
    public DarkKingdomCurse(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    private static final int SPAWN_COOLDOWN = 200;

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
        return ModConfig.ServerConfig.darkKingdomConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
            return ModConfig.ServerConfig.darkKingdomConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.darkKingdomConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.darkKingdomConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START || event.player.getCommandSenderWorld().isClientSide())
            return;

        Player player = event.player;
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        if (EnchantUtils.hasEnch(chest, ModEnchantments.DARK_KINGDOM_CURSE)) {
            int radius = 32;
            Level world = player.getCommandSenderWorld();

            for (Mob mob : world.getEntitiesOfClass(Mob.class, player.getBoundingBox().inflate(radius))) {
                double distanceSq = player.distanceToSqr(mob);
                if (distanceSq <= radius * radius) {
                    mob.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 99999, 3, false, false));
                    mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 99999, 2, false, false));
                    mob.setTarget(player);
                }
            }
        }
    }
}