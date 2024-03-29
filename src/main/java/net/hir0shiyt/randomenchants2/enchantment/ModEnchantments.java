package net.hir0shiyt.randomenchants2.enchantment;

import net.hir0shiyt.randomenchants2.RandomEnchants2;
import net.hir0shiyt.randomenchants2.curse.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, RandomEnchants2.MOD_ID);

    //CREATE ENCHANTMENT CATEGORIES
    public static final EnchantmentCategory SWORDS_BOWS = EnchantmentCategory.create("weapons", item -> item instanceof SwordItem || item instanceof BowItem || item instanceof CrossbowItem);
    public static final EnchantmentCategory PICKAXE = EnchantmentCategory.create("pickaxes", PickaxeItem.class::isInstance);
    public static final EnchantmentCategory SHOOTABLE = EnchantmentCategory.create("shootable", item -> item instanceof BowItem || item instanceof CrossbowItem);
    public static final EnchantmentCategory SHIELDS = EnchantmentCategory.create("shields", ShieldItem.class::isInstance);
    public static final EnchantmentCategory AXES = EnchantmentCategory.create("axes", AxeItem.class::isInstance);
    public static final EnchantmentCategory TOOLSANDWEAPONS = EnchantmentCategory.create("tools&weapons", item -> item instanceof SwordItem || item instanceof AxeItem || item instanceof ShovelItem || item instanceof HoeItem || item instanceof BowItem || item instanceof CrossbowItem);
    public static final EnchantmentCategory ALL_ITEMS = EnchantmentCategory.create("all_items", Item.class::isInstance);

    //REGISTER ENCHANTMENTS
    public static final RegistryObject<Enchantment> SOLAR_ENCHANT = ENCHANTMENTS.register("solar_enchant", () -> new SolarEnchant(Enchantment.Rarity.VERY_RARE, ModEnchantments.ALL_ITEMS, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}));
    public static final RegistryObject<Enchantment> OBSIDIAN_BUSTER = ENCHANTMENTS.register("obsidian_buster", () -> new ObsidianBuster(Enchantment.Rarity.VERY_RARE, ModEnchantments.PICKAXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> RANDOMNESS = ENCHANTMENTS.register("randomness", () -> new Randomness(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> MAGNETIC = ENCHANTMENTS.register("magnetic", () -> new Magnetic(Enchantment.Rarity.RARE, ModEnchantments.TOOLSANDWEAPONS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> STONE_LOVER = ENCHANTMENTS.register("stone_lover", () -> new StoneLover(Enchantment.Rarity.RARE, ModEnchantments.PICKAXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> RESISTANT = ENCHANTMENTS.register("resistant", () -> new Resistant(Enchantment.Rarity.VERY_RARE, ModEnchantments.ALL_ITEMS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> ETERNAL = ENCHANTMENTS.register("eternal", () -> new Eternal(Enchantment.Rarity.VERY_RARE, ModEnchantments.ALL_ITEMS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> DUNGEONEERING = ENCHANTMENTS.register("dungeoneering", () -> new Dungeoneering(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> GRAPPLING = ENCHANTMENTS.register("grappling", () -> new Grappling(Enchantment.Rarity.RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> SNATCHING = ENCHANTMENTS.register("snatching", () -> new Snatching(Enchantment.Rarity.RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> TELEPORTATION = ENCHANTMENTS.register("teleportation", () -> new Teleportation(Enchantment.Rarity.VERY_RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> TORCHES = ENCHANTMENTS.register("torches", () -> new Torches(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> TRUE_SHOT = ENCHANTMENTS.register("true_shot", () -> new TrueShot(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> EQUAL_MINE = ENCHANTMENTS.register("equal_mine", () -> new EqualMine(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> TRANSPOSITION = ENCHANTMENTS.register("transposition", () -> new Transposition(Enchantment.Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> RICOCHET = ENCHANTMENTS.register("ricochet", () -> new Ricochet(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> EXPLODING = ENCHANTMENTS.register("exploding", () -> new Exploding(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> BACK_TO_THE_CHAMBER = ENCHANTMENTS.register("back_to_the_chamber", () -> new BackToTheChamber(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> QUICK_DRAW = ENCHANTMENTS.register("quick_draw", () -> new QuickDraw(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> DISCORD = ENCHANTMENTS.register("discord", () -> new Discord(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> SWIFT = ENCHANTMENTS.register("swift", () -> new Swift(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> DISARM = ENCHANTMENTS.register("disarm", () -> new Disarm(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> SHATTERING = ENCHANTMENTS.register("shattering", () -> new Shattering(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> HOMING = ENCHANTMENTS.register("homing", () -> new Homing(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> PARALYSIS = ENCHANTMENTS.register("paralysis", () -> new Paralysis(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> CURSED_JUMPING = ENCHANTMENTS.register("cursed_jumping", () -> new CursedJumping(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> DEFLECT = ENCHANTMENTS.register("deflect", () -> new Deflect(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST}));
    public static final RegistryObject<Enchantment> FLOATING = ENCHANTMENTS.register("floating", () -> new Floating(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> HARVEST = ENCHANTMENTS.register("harvest", () -> new Harvest(Enchantment.Rarity.RARE, ModEnchantments.SHOOTABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> INSTANT_DEATH = ENCHANTMENTS.register("instant_death", () -> new InstantDeath(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> LIGHTNING = ENCHANTMENTS.register("lightning", () -> new Lightning(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> LUMBERJACK = ENCHANTMENTS.register("lumberjack", () -> new Lumberjack(Enchantment.Rarity.RARE, ModEnchantments.AXES, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> TRUE_LIFE_STEAL = ENCHANTMENTS.register("true_life_steal", () -> new TrueLifeSteal(Enchantment.Rarity.RARE, ModEnchantments.SWORDS_BOWS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> REFLECT = ENCHANTMENTS.register("reflect", () -> new Reflect(Enchantment.Rarity.RARE, ModEnchantments.SHIELDS, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));
    public static final RegistryObject<Enchantment> STONE_BOUND = ENCHANTMENTS.register("stone_bound", () -> new StoneBound(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> CHAOS_STRIKE = ENCHANTMENTS.register("chaos_strike", () -> new ChaosStrike(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> ETHEREAL_EMBRACE = ENCHANTMENTS.register("ethereal_embrace", () -> new EtherealEmbrace(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST}));
    public static final RegistryObject<Enchantment> DIMENSIONAL_SHUFFLE = ENCHANTMENTS.register("dimensional_shuffle", () -> new DimensionalShuffle(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST}));
    public static final RegistryObject<Enchantment> ZEN_SANCTUARY = ENCHANTMENTS.register("zen_sanctuary", () -> new ZenSanctuary(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST}));


    // CURSES REGISTRIES
    public static final RegistryObject<Enchantment> BREAKING_CURSE = ENCHANTMENTS.register("breaking_curse", () -> new BreakingCurse(Enchantment.Rarity.RARE, ModEnchantments.ALL_ITEMS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> BUTTER_FINGERS_CURSE = ENCHANTMENTS.register("butter_fingers_curse", () -> new ButterFingersCurse(Enchantment.Rarity.RARE, ModEnchantments.ALL_ITEMS, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> FUMBLING_CURSE = ENCHANTMENTS.register("fumbling_curse", () -> new FumblingCurse(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final RegistryObject<Enchantment> SHADOWS_CURSE = ENCHANTMENTS.register("shadows_curse", () -> new ShadowsCurse(Enchantment.Rarity.RARE, ModEnchantments.ALL_ITEMS, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));
    public static final RegistryObject<Enchantment> LINGERING_SHADOWS_CURSE = ENCHANTMENTS.register("lingering_shadows_curse", () -> new LingeringShadowsCurse(Enchantment.Rarity.VERY_RARE, ModEnchantments.TOOLSANDWEAPONS, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));
    public static final RegistryObject<Enchantment> DARK_KINGDOM_CURSE = ENCHANTMENTS.register("dark_kingdom_curse", () -> new DarkKingdomCurse(Enchantment.Rarity.RARE, ModEnchantments.ALL_ITEMS, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}