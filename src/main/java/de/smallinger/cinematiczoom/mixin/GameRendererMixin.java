package de.smallinger.cinematiczoom.mixin;

import de.smallinger.cinematiczoom.ZoomManager;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to modify the FOV based on zoom state.
 * Ported from Fabric to NeoForge.
 */
@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "getFov(Lnet/minecraft/client/Camera;FZ)F",
            at = @At("RETURN"), cancellable = true)
    private void cinematiczoom$applyZoom(Camera camera, float tickDelta, boolean changingFov,
                                         CallbackInfoReturnable<Float> cir) {
        ZoomManager.frameUpdate();

        Minecraft client = Minecraft.getInstance();
        if (client.screen != null) return;

        float fov = cir.getReturnValue();
        double mul = ZoomManager.getCurrentFovMul();
        if (mul != 1.0) {
            cir.setReturnValue((float) (fov * mul));
        }
    }
}
