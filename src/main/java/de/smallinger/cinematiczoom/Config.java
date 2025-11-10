package de.smallinger.cinematiczoom;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * CinematicZoom configuration using NeoForge's ModConfigSpec system.
 * Ported from Fabric's JSON-based config to NeoForge's TOML-based config.
 */
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // --- User Settings ---
    
    /** Height of black bars in percent of screen height (total 2 * value) */
    public static final ModConfigSpec.DoubleValue BARS_PERCENT = BUILDER
            .comment("Height of black bars in percent of screen height (total 2 * value)")
            .defineInRange("barsPercent", 15.0, 0.0, 50.0);

    /** Smoothing speed: time to reach ~90% to target, milliseconds. 0 = instant */
    public static final ModConfigSpec.IntValue SMOOTH_MS = BUILDER
            .comment("Smoothing speed: time to reach ~90% to target, milliseconds. 0 = instant")
            .defineInRange("smoothMs", 240, 0, 2000);

    /** Allow zoom control with mouse wheel while holding */
    public static final ModConfigSpec.BooleanValue MOUSE_WHEEL_ENABLED = BUILDER
            .comment("Allow zoom control with mouse wheel while holding zoom key")
            .define("mouseWheelEnabled", true);

    /** Hide HUD/crosshair during zoom */
    public static final ModConfigSpec.BooleanValue HIDE_HUD_DURING_ZOOM = BUILDER
            .comment("Hide HUD/crosshair during zoom")
            .define("hideHudDuringZoom", true);

    /** Enable cinematic camera (smooth camera) during zoom */
    public static final ModConfigSpec.BooleanValue ENABLE_CINEMATIC_CAMERA = BUILDER
            .comment("Enable cinematic camera (smooth camera) during zoom")
            .define("enableCinematicCamera", true);

    // --- Internal (not shown in config screen, but stored) ---
    
    /** Base FOV multiplier when starting zoom (less than 1 = zoom in) */
    public static final ModConfigSpec.DoubleValue BASE_ZOOM_MULTIPLIER = BUILDER
            .comment("Base FOV multiplier when starting zoom (less than 1 = zoom in)")
            .defineInRange("baseZoomMultiplier", 0.33, 0.05, 1.0);

    /** Minimum zoom multiplier when using mouse wheel */
    public static final ModConfigSpec.DoubleValue MIN_ZOOM_MULTIPLIER = BUILDER
            .comment("Minimum zoom multiplier when using mouse wheel")
            .defineInRange("minZoomMultiplier", 0.10, 0.05, 1.0);

    /** Maximum zoom multiplier when using mouse wheel */
    public static final ModConfigSpec.DoubleValue MAX_ZOOM_MULTIPLIER = BUILDER
            .comment("Maximum zoom multiplier when using mouse wheel")
            .defineInRange("maxZoomMultiplier", 1.00, 0.05, 1.0);

    /** Step for changing multiplier per mouse wheel tick */
    public static final ModConfigSpec.DoubleValue WHEEL_STEP = BUILDER
            .comment("Step for changing multiplier per mouse wheel tick")
            .defineInRange("wheelStep", 0.05, 0.01, 0.25);

    static final ModConfigSpec SPEC = BUILDER.build();
}
