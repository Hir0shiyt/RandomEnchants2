package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.config.ModConfig;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            return ModConfig.ServerConfig.randomnessConfig.get() != ModConfig.Restriction.DISABLED && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return ModConfig.ServerConfig.randomnessConfig.get() == ModConfig.Restriction.ENABLED;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfig.ServerConfig.randomnessConfig.get() == ModConfig.Restriction.TREASURE;
    }

    @Override
    protected boolean checkCompatibility (Enchantment enchantment) {
        return !(enchantment instanceof SolarEnchant) &&
                !(enchantment instanceof Magnetic) &&
                super.checkCompatibility(enchantment);
    }

    public static Enchantment getRandomnessEnchant() {
        return ModEnchantments.RANDOMNESS;
    }

    public static List<ItemStack> getRandomItems(Random random, int level) {
        List<ItemStack> drops = new ArrayList<>();
        drops.addAll(getNormalBlockItems(random, level));
        drops.addAll(getRandomEnchantmentItems(random, level));
        return drops;
    }

    private static List<ItemStack> getNormalBlockItems(Random random, int level) {
        return new ArrayList<>();
    }

    private static List<ItemStack> getRandomEnchantmentItems(Random random, int level) {
        List<ItemStack> drops = new ArrayList<>();
        for (int i = 0; i < level; i++) {
            Item randomItem = getRandomItem(random);
            if (randomItem != null) {
                int randomCount = 1 + random.nextInt(level); // Random drop count based on enchantment level
                ItemStack drop = new ItemStack(randomItem, randomCount);
                drops.add(drop);
            }
        }
        return drops;
    }

    private static Item getRandomItem(Random random) {
        List<Item> items = new ArrayList<>();
        for (Item item : Registry.ITEM) {
            items.add(item);
        }
        return items.isEmpty() ? null : items.get(random.nextInt(items.size()));
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getMainHandItem();

        if (EnchantmentHelper.getItemEnchantmentLevel(Randomness.getRandomnessEnchant(), stack) <= 0 || event.getWorld().isClientSide()) {
            return;
        }

        Level world = (Level) event.getWorld();
        int level = EnchantmentHelper.getItemEnchantmentLevel(Randomness.getRandomnessEnchant(), stack);
        BlockState blockState = world.getBlockState(event.getPos());
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