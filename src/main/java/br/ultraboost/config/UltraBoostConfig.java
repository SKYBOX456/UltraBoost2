package br.ultraboost.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class UltraBoostConfig {
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLE_ENTITY_CULLING;
    public static final ForgeConfigSpec.DoubleValue ENTITY_RENDER_DISTANCE;
    public static final ForgeConfigSpec.BooleanValue CULL_PLAYER_ENTITIES;
    public static final ForgeConfigSpec.BooleanValue CULL_FAR_NAME_TAGS;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("performance");
        ENABLE_ENTITY_CULLING = builder
                .comment("Skip rendering living entities farther than the configured distance.")
                .define("enableEntityCulling", true);

        ENTITY_RENDER_DISTANCE = builder
                .comment("Maximum render distance for living entities, in blocks. Lower values can improve FPS but make distant mobs disappear.")
                .defineInRange("entityRenderDistance", 96.0D, 16.0D, 512.0D);

        CULL_PLAYER_ENTITIES = builder
                .comment("Also cull other player models at long range. Usually best left disabled on multiplayer servers.")
                .define("cullPlayerEntities", false);

        CULL_FAR_NAME_TAGS = builder
                .comment("Hide living-entity name tags beyond the entity render distance.")
                .define("cullFarNameTags", true);
        builder.pop();

        SPEC = builder.build();
    }

    private UltraBoostConfig() {}
}
