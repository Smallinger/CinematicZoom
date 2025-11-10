package de.smallinger.cinematiczoom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.KeyMapping;

/**
 * Core zoom management logic.
 * Handles zoom state, smoothing, black bars rendering, and mouse wheel control.
 * Ported from Fabric to NeoForge.
 */
public class ZoomManager {

    private static boolean zoomHeld = false;

    private static float currentZoomMul = 1.0f;
    private static float targetZoomMul = 1.0f;
    private static float holdZoomMul = 0.33f; // Will be loaded from config

    private static float currentBarsPct = 0f;
    private static float targetBarsPct = 0f;

    private static long lastNs = 0L;

    private static Boolean prevHudHidden = null;
    private static Boolean prevSmoothCamera = null;

    public static void tick(Minecraft client, KeyMapping key) {
        boolean inScreen = client.screen != null;
        boolean wantZoom = key.isDown() && !inScreen;

        boolean starting = wantZoom && !zoomHeld;
        boolean ending = !wantZoom && zoomHeld;

        if (starting) {
            holdZoomMul = clamp(Config.BASE_ZOOM_MULTIPLIER.get().floatValue(),
                    Config.MIN_ZOOM_MULTIPLIER.get().floatValue(),
                    Config.MAX_ZOOM_MULTIPLIER.get().floatValue());

            // Hide HUD/crosshair
            if (Config.HIDE_HUD_DURING_ZOOM.get()) {
                prevHudHidden = client.options.hideGui;
                client.options.hideGui = true;
            }
            // Enable cinematic camera
            if (Config.ENABLE_CINEMATIC_CAMERA.get()) {
                prevSmoothCamera = client.options.smoothCamera;
                client.options.smoothCamera = true;
            }
        }

        if (ending) {
            // Restore HUD
            if (prevHudHidden != null) {
                client.options.hideGui = prevHudHidden;
                prevHudHidden = null;
            }
            // Restore camera
            if (prevSmoothCamera != null) {
                client.options.smoothCamera = prevSmoothCamera;
                prevSmoothCamera = null;
            }
        }

        zoomHeld = wantZoom;

        targetZoomMul = zoomHeld ? holdZoomMul : 1.0f;
        targetBarsPct = zoomHeld ? Config.BARS_PERCENT.get().floatValue() : 0f;
    }

    public static void frameUpdate() {
        final int smooth = Config.SMOOTH_MS.get();

        long now = System.nanoTime();
        if (lastNs == 0L) {
            lastNs = now;
            return;
        }
        double dtMs = (now - lastNs) / 1_000_000.0;
        lastNs = now;
        if (dtMs > 50) dtMs = 50;

        if (smooth <= 0) {
            currentZoomMul = targetZoomMul;
            currentBarsPct = targetBarsPct;
            return;
        }

        final double tau = smooth / 2.302585092994046;
        final double alpha = 1.0 - Math.exp(-dtMs / tau);

        currentZoomMul = (float) lerp(currentZoomMul, targetZoomMul, alpha);
        currentBarsPct = (float) lerp(currentBarsPct, targetBarsPct, alpha);

        if (Math.abs(currentZoomMul - targetZoomMul) < 1e-4f) currentZoomMul = targetZoomMul;
        if (Math.abs(currentBarsPct - targetBarsPct) < 1e-3f) currentBarsPct = targetBarsPct;
    }

    public static boolean isZoomHeld() {
        return zoomHeld;
    }

    public static double getCurrentFovMul() {
        return currentZoomMul;
    }

    public static boolean onWheel(double vertical) {
        if (!zoomHeld || !Config.MOUSE_WHEEL_ENABLED.get()) return false;
        if (vertical == 0) return false;

        float step = Config.WHEEL_STEP.get().floatValue();
        if (vertical > 0) holdZoomMul -= step;
        else holdZoomMul += step;

        holdZoomMul = clamp(holdZoomMul, 
            Config.MIN_ZOOM_MULTIPLIER.get().floatValue(), 
            Config.MAX_ZOOM_MULTIPLIER.get().floatValue());
        return true;
    }

    public static void renderBars(GuiGraphics guiGraphics) {
        if (currentBarsPct <= 0.0001f) return;

        int sw = guiGraphics.guiWidth();
        int sh = guiGraphics.guiHeight();

        int h = Math.round(sh * (currentBarsPct / 100f));
        if (h <= 0) return;

        int color = 0xFF000000;
        guiGraphics.fill(0, 0, sw, h, color);
        guiGraphics.fill(0, sh - h, sw, sh, color);
    }

    private static double lerp(double a, double b, double t) {
        if (t <= 0) return a;
        if (t >= 1) return b;
        return a + (b - a) * t;
    }

    private static float clamp(float v, float min, float max) {
        return Math.max(min, Math.min(max, v));
    }
}
