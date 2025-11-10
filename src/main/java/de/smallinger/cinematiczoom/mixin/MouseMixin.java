package de.smallinger.cinematiczoom.mixin;

import de.smallinger.cinematiczoom.ZoomManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to intercept mouse scroll events for zoom control.
 * Ported from Fabric to NeoForge.
 */
@Mixin(MouseHandler.class)
public class MouseMixin {

    @Shadow @Final private Minecraft minecraft;

    /**
     * Intercept mouse wheel: during zoom hold - control multiplier and block hotbar scrolling.
     */
    @Inject(method = "onScroll(JDD)V", at = @At("HEAD"), cancellable = true)
    private void cinematiczoom$onScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (minecraft == null) return;
        if (minecraft.screen != null) return; // Don't interfere in GUI

        if (ZoomManager.onWheel(vertical)) {
            ci.cancel(); // Cancel standard handler (to prevent hotbar scrolling)
        }
    }
}
