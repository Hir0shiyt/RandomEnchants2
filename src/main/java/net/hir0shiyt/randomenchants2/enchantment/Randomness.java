package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID)
public class Randomness extends Enchantment {
    public Randomness(Enchantment.Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }


    @Override
    public int getMinCost(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ModConfig.ServerConfig.randomnessConfig.get() != ModConfig.Restriction.DISABLED && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        if (stack.getItem() instanceof PickaxeItem && stack.getItem() instanceof ShovelItem && stack.getItem() instanceof AxeItem && stack.getItem() instanceof HoeItem) {
            return ModConfig.ServerConfig.randomnessConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
        } else {
            return false;
        }
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.randomnessConfig.get() == ModConfig.Restriction.NORMAL;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.randomnessConfig.get() == ModConfig.Restriction.ANVIL;
    }

    @Override
    protected boolean checkCompatibility (Enchantment enchantment) {
        return !(enchantment instanceof SolarEnchant) &&
                !(enchantment instanceof Magnetic) &&
                super.checkCompatibility(enchantment);
    }

    public static Enchantment getRandomnessEnchant() {
        return ModEnchantments.RANDOMNESS.get();
    }

    public static List<ItemStack> getRandomItems(RandomSource randomSource, int level) {
        List<ItemStack> drops = new ArrayList<>();
        drops.addAll(getNormalBlockItems(randomSource, level));
        drops.addAll(getRandomEnchantmentItems(randomSource, level));
        return drops;
    }

    private static List<ItemStack> getNormalBlockItems(RandomSource randomSource, int level) {
        return new ArrayList<>();
    }

    private static List<ItemStack> getRandomEnchantmentItems(RandomSource randomSource, int level) {
        List<ItemStack> drops = new ArrayList<>();
        Item randomItem = getRandomItem(randomSource);
        if (randomItem != null) {
            int randomCount = 1 + randomSource.nextInt(level);
            ItemStack drop = new ItemStack(randomItem, randomCount);
            drops.add(drop);
        }
        return drops;
    }

    private static Item getRandomItem(RandomSource randomSource) {
        List<Item> items = new ArrayList<>();
        for (Item item : ForgeRegistries.ITEMS) {
            items.add(item);
        }
        return items.isEmpty() ? null : items.get(randomSource.nextInt(items.size()));
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getMainHandItem();

        if (EnchantmentHelper.getItemEnchantmentLevel(Randomness.getRandomnessEnchant(), stack) <= 0 || event.getLevel().isClientSide()) {
            return;
        }

        Level world = (Level) event.getLevel();
        int level = EnchantmentHelper.getItemEnchantmentLevel(Randomness.getRandomnessEnchant(), stack);
        event.setCanceled(true);
        world.destroyBlock(event.getPos(), false);
        event.setExpToDrop(0);
        List<ItemStack> drops = Randomness.getRandomItems(world.random, level);

        for (ItemStack drop : drops) {
            if (!drop.isEmpty()) {
                ItemHandlerHelper.giveItemToPlayer(player, drop);
            }
        }
    }
}