package de.smallinger.cinematiczoom.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.DeltaTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to render black bars during zoom.
 * Renders bars even when HUD is hidden (F1).
 * Ported from Fabric to NeoForge.
 */
@Mixin(Gui.class)
public class InGameHudMixin {

    // Method signature for 1.21.x: render(GuiGraphics, DeltaTracker) -> void
    @Inject(method = "render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At("HEAD"))
    private void cinematiczoom$drawBarsHead(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        // Don't call frameUpdate() here to avoid accelerating smoothing (it's already called in getFov)
        de.smallinger.cinematiczoom.ZoomManager.renderBars(guiGraphics);
    }
}
