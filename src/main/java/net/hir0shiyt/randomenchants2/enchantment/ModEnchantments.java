package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.curse.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

import static net.hir0shiyt.randomenchants2.RandomEnchants2.enchants;

@Mod.EventBusSubscriber(modid = RandomEnchants2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments {

    //CREATE ENCHANTMENT CATEGORIES
    public static final EnchantmentCategory SWORDS_BOWS = EnchantmentCategory.create("weapons", item -> item instanceof SwordItem || item instanceof BowItem || item instanceof CrossbowItem);
    public static final EnchantmentCategory PICKAXE = EnchantmentCategory.create("pickaxes", PickaxeItem.class::isInstance);
    public static final EnchantmentCategory SHOOTABLE = EnchantmentCategory.create("shootable", item -> item instanceof BowItem || item instanceof CrossbowItem);
    public static final EnchantmentCategory SHIELDS = EnchantmentCategory.create("shields", ShieldItem.class::isInstance);
    public static final EnchantmentCategory AXES = EnchantmentCategory.create("axes", AxeItem.class::isInstance);
    public static final EnchantmentCategory TOOLSANDWEAPONS = EnchantmentCategory.create("tools&weapons", item -> item instanceof SwordItem || item instanceof AxeItem || item instanceof ShovelItem || item instanceof HoeItem || item instanceof BowItem || item instanceof CrossbowItem);
    public static final EnchantmentCategory ALL_ITEMS = EnchantmentCategory.create("all_items", Item.class::isInstance);

    //REGISTER ENCHANTMENTS
    public static final Enchantment SOLAR_ENCHANT = new SolarEnchant(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    public static final Enchantment OBSIDIAN_BUSTER = new ObsidianBuster(Enchantment.Rarity.VERY_RARE, ModEnchantments.PICKAXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment RANDOMNESS = new Randomness(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment MAGNETIC = new Magnetic(Enchantment.Rarity.RARE, ModEnchantments.TOOLSANDWEAPONS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment STONE_LOVER = new StoneLover(Enchantment.Rarity.RARE, ModEnchantments.PICKAXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment RESISTANT = new Resistant(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment ETERNAL = new Eternal(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment DUNGEONEERING = new Dungeoneering(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment GRAPPLING = new Grappling(Enchantment.Rarity.RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment SNATCHING = new Snatching(Enchantment.Rarity.RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment TELEPORTATION = new Teleportation(Enchantment.Rarity.VERY_RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment TORCHES = new Torches(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment TRUE_SHOT = new TrueShot(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment EQUAL_MINE = new EqualMine(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment TRANSPOSITION = new Transposition(Enchantment.Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment RICOCHET = new Ricochet(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment EXPLODING = new Exploding(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment BACK_TO_THE_CHAMBER = new BackToTheChamber(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment QUICK_DRAW = new QuickDraw(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment ZEN_SANCTUARY = new ZenSanctuary(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static final Enchantment DISCORD = new Discord(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment SWIFT = new Swift(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment DISARM = new Disarm(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment SHATTERING = new Shattering(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment HOMING = new Homing(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment PARALYSIS = new Paralysis(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment CURSED_JUMPING = new CursedJumping(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment DEFLECT = new Deflect(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static final Enchantment FLOATING = new Floating(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment HARVEST = new Harvest(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment INSTANT_DEATH = new InstantDeath(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment LIGHTNING = new Lightning(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment LUMBERJACK = new Lumberjack(Enchantment.Rarity.RARE, ModEnchantments.AXES, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment TRUE_LIFE_STEAL = new TrueLifeSteal(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment REFLECT = new Reflect(Enchantment.Rarity.RARE, ModEnchantments.SHIELDS, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    public static final Enchantment STONE_BOUND = new StoneBound(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment CHAOS_STRIKE = new ChaosStrike(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment ETHEREAL_EMBRACE = new EtherealEmbrace(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static final Enchantment DIMENSIONAL_SHUFFLE = new DimensionalShuffle(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});

    //REGISTER CURSES
    public static final Enchantment BREAKING_CURSE = new BreakingCurse(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment BUTTER_FINGERS_CURSE = new ButterFingersCurse(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment FUMBLING_CURSE = new FumblingCurse(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment SHADOWS_CURSE = new ShadowsCurse(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    public static final Enchantment LINGERING_SHADOWS_CURSE = new LingeringShadowsCurse(Enchantment.Rarity.VERY_RARE, ModEnchantments.TOOLSANDWEAPONS, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    public static final Enchantment DARK_KINGDOM_CURSE = new DarkKingdomCurse(Enchantment.Rarity.RARE, ModEnchantments.ALL_ITEMS, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        //ENCHANTMENTS REGISTRIES
        register(event.getRegistry(), "solar_enchant", SOLAR_ENCHANT);
        register(event.getRegistry(), "obsidian_buster", OBSIDIAN_BUSTER);
        register(event.getRegistry(), "randomness", RANDOMNESS);
        register(event.getRegistry(), "magnetic", MAGNETIC);
        register(event.getRegistry(), "stone_lover", STONE_LOVER);
        register(event.getRegistry(), "resistant", RESISTANT);
        register(event.getRegistry(), "eternal", ETERNAL);
        register(event.getRegistry(), "dungeoneering", DUNGEONEERING);
        register(event.getRegistry(), "grappling", GRAPPLING);
        register(event.getRegistry(), "snatching", SNATCHING);
        register(event.getRegistry(), "teleportation", TELEPORTATION);
        register(event.getRegistry(), "torches", TORCHES);
        register(event.getRegistry(), "true_shot", TRUE_SHOT);
        register(event.getRegistry(), "equal_mine", EQUAL_MINE);
        register(event.getRegistry(), "transposition", TRANSPOSITION);
        register(event.getRegistry(), "ricochet", RICOCHET);
        register(event.getRegistry(), "exploding", EXPLODING);
        register(event.getRegistry(), "back_to_the_chamber", BACK_TO_THE_CHAMBER);
        register(event.getRegistry(), "quick_draw", QUICK_DRAW);
        register(event.getRegistry(), "zen_sanctuary", ZEN_SANCTUARY);
        register(event.getRegistry(), "discord", DISCORD);
        register(event.getRegistry(), "swift", SWIFT);
        register(event.getRegistry(), "disarm", DISARM);
        register(event.getRegistry(), "shattering", SHATTERING);
        register(event.getRegistry(), "homing", HOMING);
        register(event.getRegistry(), "paralysis", PARALYSIS);
        register(event.getRegistry(), "cursed_jumping", CURSED_JUMPING);
        register(event.getRegistry(), "deflect", DEFLECT);
        register(event.getRegistry(), "floating", FLOATING);
        register(event.getRegistry(), "harvest", HARVEST);
        register(event.getRegistry(), "instant_death", INSTANT_DEATH);
        register(event.getRegistry(), "lightning", LIGHTNING);
        register(event.getRegistry(), "lumberjack", LUMBERJACK);
        register(event.getRegistry(), "true_life_steal", TRUE_LIFE_STEAL);
        register(event.getRegistry(), "reflect", REFLECT);
        register(event.getRegistry(), "stone_bound", STONE_BOUND);
        register(event.getRegistry(), "chaos_strike", CHAOS_STRIKE);
        register(event.getRegistry(), "ethereal_embrace", ETHEREAL_EMBRACE);
        register(event.getRegistry(), "dimensional_shuffle", DIMENSIONAL_SHUFFLE);

        //CURSE REGISTRIES
        register(event.getRegistry(), "breaking_curse", BREAKING_CURSE);
        register(event.getRegistry(), "butter_fingers_curse", BUTTER_FINGERS_CURSE);
        register(event.getRegistry(), "fumbling_curse", FUMBLING_CURSE);
        register(event.getRegistry(), "shadows_curse", SHADOWS_CURSE);
        register(event.getRegistry(), "lingering_shadows_curse", LINGERING_SHADOWS_CURSE);
        register(event.getRegistry(), "dark_kingdom_curse", DARK_KINGDOM_CURSE);


        //register logger for very useful purposes, yeah....
        IForgeRegistry<Enchantment> r = event.getRegistry();

        for (Enchantment enchantment : enchants)
            r.register(enchantment);

    }
    //why r u reading this?
    private static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object) {
        object.setRegistryName(RandomEnchants2.getLocation(name));
        registry.register(object);
    }
}