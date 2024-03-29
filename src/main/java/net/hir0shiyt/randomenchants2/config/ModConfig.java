package net.hir0shiyt.randomenchants2.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    public static class ServerConfig {

        public static ForgeConfigSpec.EnumValue<Restriction> solarEnchantConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> obsidianBusterConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> randomnessConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> magneticConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> stoneLoverConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> eternalConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> dungeoneeringConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> grapplingConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> snatchingConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> resistantConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> teleportationConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> torchesConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> trueShotConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> equalMineConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> assimilationConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> transpositionConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> ricochetConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> explodingConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> backToTheChamberConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> quickDrawConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> discordConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> swiftConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> disarmConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> shatteringConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> homingConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> paralysisConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> cursedJumpingConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> deflectConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> floatingConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> harvestConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> instantDeathConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> lightningConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> lumberjackConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> trueLifeStealConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> reflectConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> stoneBoundConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> chaosStrikeConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> etherealEmbraceConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> dimensionalShuffleConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> zenSanctuaryConfig;

        //curses
        public static ForgeConfigSpec.EnumValue<Restriction> breakingConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> butterFingersConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> fumblingConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> shadowsConfig;
        public static ForgeConfigSpec.EnumValue<Restriction> lingeringShadowsConfig;

        //extra
        public static ForgeConfigSpec.EnumValue<Restriction2> dungeoneeringLootTableConfig;

        ServerConfig(ForgeConfigSpec.Builder builder) {
            builder.push("enchantments")
                    .comment("Use TREASURE for the enchantment or curse to only be achieved via treasures")
                    .comment("Use ENABLED for the enchantment or curse to be achievable")
                    .comment("Use DISABLED to deactivate the enchantment or curse");

            solarEnchantConfig = builder.comment("Restriction for Solar Enchantment")
                    .defineEnum("solarEnchantConfig", ModConfig.Restriction.ENABLED);
            obsidianBusterConfig = builder.comment("Restriction for Obsidian Buster Enchantment")
                    .defineEnum("obsidianBusterConfig", ModConfig.Restriction.ENABLED);
            randomnessConfig = builder.comment("Restriction for Randomness Enchantment")
                    .defineEnum("randomnessConfig", ModConfig.Restriction.ENABLED);
            magneticConfig = builder.comment("Restriction for Magnetic Enchantment")
                    .defineEnum("magneticConfig", ModConfig.Restriction.ENABLED);
            stoneLoverConfig = builder.comment("Restriction for Stone Lover Enchantment")
                    .defineEnum("stoneLoverConfig", ModConfig.Restriction.ENABLED);
            eternalConfig = builder.comment("Restriction for Eternal Enchantment")
                    .defineEnum("eternalConfig", ModConfig.Restriction.ENABLED);
            dungeoneeringConfig = builder.comment("Restriction for Dungeoneering Enchantment")
                    .defineEnum("dungeoneeringConfig", ModConfig.Restriction.ENABLED);
            grapplingConfig = builder.comment("Restriction for Grappling Enchantment")
                    .defineEnum("grapplingConfig", ModConfig.Restriction.ENABLED);
            snatchingConfig = builder.comment("Restriction for Snatching Enchantment")
                    .defineEnum("snatchingConfig", ModConfig.Restriction.ENABLED);
            resistantConfig = builder.comment("Restriction for Resistant Enchantment")
                    .defineEnum("resistantConfig", ModConfig.Restriction.ENABLED);
            teleportationConfig = builder.comment("Restriction for Teleportation Enchantment")
                    .defineEnum("teleportationConfig", ModConfig.Restriction.ENABLED);
            torchesConfig = builder.comment("Restriction for Torches Enchantment")
                    .defineEnum("torchesConfig", ModConfig.Restriction.ENABLED);
            trueShotConfig = builder.comment("Restriction for True Shot Enchantment")
                    .defineEnum("trueShotConfig", ModConfig.Restriction.ENABLED);
            equalMineConfig = builder.comment("Restriction for Equal Mine Enchantment")
                    .defineEnum("equalMineConfig", ModConfig.Restriction.ENABLED);
            assimilationConfig = builder.comment("Restriction for Assimilation Enchantment")
                    .defineEnum("assimilationConfig", ModConfig.Restriction.ENABLED);
            transpositionConfig = builder.comment("Restriction for Transposition Enchantment")
                    .defineEnum("transpositionConfig", ModConfig.Restriction.ENABLED);
            ricochetConfig = builder.comment("Restriction for Ricochet Enchantment")
                    .defineEnum("ricochetConfig", ModConfig.Restriction.ENABLED);
            explodingConfig = builder.comment("Restriction for Exploding Enchantment")
                    .defineEnum("explodingConfig", ModConfig.Restriction.ENABLED);
            backToTheChamberConfig = builder.comment("Restriction for Back To The Chamber Enchantment")
                    .defineEnum("backToTheChamberConfig", ModConfig.Restriction.ENABLED);
            quickDrawConfig = builder.comment("Restriction for Quick Draw Enchantment")
                    .defineEnum("quickDrawConfig", ModConfig.Restriction.ENABLED);
            discordConfig = builder.comment("Restriction for Discord Enchantment")
                    .defineEnum("discordConfig", ModConfig.Restriction.ENABLED);
            swiftConfig = builder.comment("Restriction for Swift Enchantment")
                    .defineEnum("swiftConfig", ModConfig.Restriction.ENABLED);
            disarmConfig = builder.comment("Restriction for Disarm Enchantment")
                    .defineEnum("disarmConfig", ModConfig.Restriction.ENABLED);
            shatteringConfig = builder.comment("Restriction for Shattering Enchantment")
                    .defineEnum("shatteringConfig", ModConfig.Restriction.ENABLED);
            homingConfig = builder.comment("Restriction for Homing Enchantment")
                    .defineEnum("homingConfig", ModConfig.Restriction.ENABLED);
            paralysisConfig = builder.comment("Restriction for Paralysis Enchantment")
                    .defineEnum("paralysisConfig", ModConfig.Restriction.ENABLED);
            cursedJumpingConfig = builder.comment("Restriction for Cursed Jumping Enchantment")
                    .defineEnum("cursedJumpConfig", ModConfig.Restriction.ENABLED);
            deflectConfig = builder.comment("Restriction for Deflect Enchantment")
                    .defineEnum("reflectConfig", Restriction.ENABLED);
            floatingConfig = builder.comment("Restriction for Floating Enchantment")
                    .defineEnum("floatingConfig", Restriction.ENABLED);
            harvestConfig = builder.comment("Restriction for Harvest Enchantment")
                    .defineEnum("harvestConfig", Restriction.ENABLED);
            instantDeathConfig = builder.comment("Restriction for Instant Death Enchantment")
                    .defineEnum("instantDeathConfig", Restriction.ENABLED);
            lightningConfig = builder.comment("Restriction for Lighting Enchantment")
                    .defineEnum("lightningConfig", Restriction.ENABLED);
            lumberjackConfig = builder.comment("Restriction for Lumberjack Enchantment")
                    .defineEnum("lumberjackConfig", Restriction.ENABLED);
            trueLifeStealConfig = builder.comment("Restriction for True Life Steal Enchantment")
                    .defineEnum("trueLifeStealConfig", Restriction.ENABLED);
            reflectConfig = builder.comment("Restriction for Reflect Enchantment")
                    .defineEnum("reflectConfig", Restriction.ENABLED);
            stoneBoundConfig = builder.comment("Restriction for Stone Bound Enchantment")
                    .defineEnum("stoneBoundConfig", Restriction.ENABLED);


            builder.pop();
            builder.push("curses");

            breakingConfig = builder.comment("Restriction for Breaking Curse")
                    .defineEnum("breakingConfig", Restriction.ENABLED);
            butterFingersConfig = builder.comment("Restriction for Butter Fingers Curse")
                    .defineEnum("butterFingersConfig", Restriction.ENABLED);
            fumblingConfig = builder.comment("Restriction for Fumbling Curse")
                    .defineEnum("fumblingConfig", Restriction.ENABLED);
            shadowsConfig = builder.comment("Restriction for Shadows Curse")
                    .defineEnum("shadowsConfig", Restriction.ENABLED);



            builder.pop();
            builder.push("unoriginal enchantments / curses");

            lingeringShadowsConfig = builder.comment("Restriction for Chaotic Shifting Curse")
                    .comment("This Curse is Disabled by Default, use ENABLED if you want to enable it!")
                    .defineEnum("lingeringShadowsConfig", Restriction.DISABLED);

            chaosStrikeConfig = builder.comment("Restriction for Chaos Strike Enchantment")
                    .comment("Extra Enchantments are Disabled by Default, use ENABLED if you want to enable them!")
                    .defineEnum("chaosStrikeConfig", Restriction.DISABLED);

            etherealEmbraceConfig = builder.comment("Restriction for Ethereal Embrace Enchantment")
                    .comment("Extra Enchantments are Disabled by Default, use ENABLED if you want to enable them!")
                    .defineEnum("etherealEmbraceConfig", Restriction.DISABLED);

            dimensionalShuffleConfig = builder.comment("Restriction for Dimensional Shuffle Enchantment")
                    .comment("Extra Enchantments are Disabled by Default, use ENABLED if you want to enable them!")
                    .defineEnum("dimensionalShuffleConfig", Restriction.DISABLED);

            zenSanctuaryConfig = builder.comment("Restriction for Zen Sanctuary Enchantment")
                    .comment("Extra Enchantments are Disabled by Default, use ENABLED if you want to enable them!")
                    .defineEnum("zenSanctuaryConfig", Restriction.DISABLED);


            builder.pop();
            builder.push("extra configurations");

            dungeoneeringLootTableConfig = builder.comment("Loot table config for dungeoneering Disabled by default")
                    .defineEnum("dungeoneeringLootTableConfig", Restriction2.DISABLED);

            builder.pop();
        }
    }
    public enum Restriction {
        DISABLED, ENABLED, TREASURE
    }
    public enum Restriction2 {
        DISABLED, ENABLED
    }
}