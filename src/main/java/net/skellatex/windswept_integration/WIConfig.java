package net.skellatex.windswept_integration;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class WIConfig {

    public static class Common {

        @ConfigKey("environmental_pine")
        public final BooleanValue environmentalPine;
        @ConfigKey("ecologics_snow_bricks")
        public final BooleanValue ecologicsSnowBricks;
        @ConfigKey("bftp_snow_bricks")
        public final BooleanValue bftpSnowBricks;

        @ConfigKey("lavender_dye_recipe")
        public final BooleanValue lavenderDyeRecipe;
        @ConfigKey("frostbite_forest_mobs")
        public final BooleanValue frostbiteForestMobs;
        @ConfigKey("frosted_caves_chilled")
        public final BooleanValue frostedCavesChilled;
        @ConfigKey("non_frosty_mobs")
        public final BooleanValue disableNonFrosty;

        @ConfigKey("elder_brush")
        public final BooleanValue elderBrush;
        @ConfigKey("chilly_moss_carpet")
        public final BooleanValue chillyMossCarpet;

        public Common(ModConfigSpec.Builder builder) {

            builder.push("content_overlap");
            this.environmentalPine = builder.comment("Disables Environmental Pine in worldgen & loot").define("Disable Environmental Pine", true);
            this.ecologicsSnowBricks = builder.comment("Disables Ecologics Snow Brick recipes").define("Disable Ecologics Snow Bricks", true);
            this.bftpSnowBricks = builder.comment("Disables Blast from the Past Snow Brick recipes").define("Disable Blast from the Past Snow Bricks", true);
            builder.pop();

            builder.push("tweaks");
            this.lavenderDyeRecipe = builder.comment("Allows crafting Magenta Dye from Lavender").define("Lavender Dye Recipe", true);
            this.frostbiteForestMobs = builder.comment("Allows Chilled and Strays to spawn in Blast from the Past's Frostbite Forest").define("Frostbite Forest Mobs", false);
            this.frostedCavesChilled = builder.comment("Whether Chilled (and Strays) should spawn instead of Zombies and Skeletons in YUNG's Frosted Caves").define("Frosted Caves Chilled", true);
            this.disableNonFrosty = builder.comment("Prevents Creepers, Spiders and Bats from spawning in YUNG's Frosted Caves").define("Disable Non-frosty Mobs", false);
            builder.pop();

            builder.push("custom_content");
            this.elderBrush = builder.comment("More durable brush variant that makes suspicious blocks drop xp when brushed").define("Elder Brush", false);
            this.chillyMossCarpet = builder.comment("Carpet for Blast from the Past's Chilly Moss").define("Chilly Moss Carpet", false);
            builder.pop();

        }
    }

    public static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

    }
}
