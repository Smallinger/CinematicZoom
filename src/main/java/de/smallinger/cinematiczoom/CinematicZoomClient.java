package de.smallinger.cinematiczoom;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.lwjgl.glfw.GLFW;

/**
 * Client-side initialization for CinematicZoom.
 * Registers keybindings and handles client tick events.
 * Ported from Fabric to NeoForge.
 */
@Mod(value = CinematicZoom.MODID, dist = Dist.CLIENT)
public class CinematicZoomClient {
    
    public static KeyMapping ZOOM_KEYBIND;

    public CinematicZoomClient(ModContainer container) {
        // Register key mapping event on mod event bus
        container.getEventBus().addListener(this::onRegisterKeyMappings);
        
        // Register tick event on forge event bus
        NeoForge.EVENT_BUS.addListener(this::onClientTick);
        
        // Register config screen factory
        container.registerExtensionPoint(IConfigScreenFactory.class, new ConfigScreenFactory());
    }

    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        // Register custom category for CinematicZoom
        KeyMapping.Category category = new KeyMapping.Category(
                net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                        CinematicZoom.MODID, 
                        "cinematiczoom"
                )
        );
        event.registerCategory(category);
        
        ZOOM_KEYBIND = new KeyMapping(
                "key.cinematiczoom.zoom",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                category
        );
        event.register(ZOOM_KEYBIND);
        
        CinematicZoom.LOGGER.info("CinematicZoom keybinding registered");
    }

    private void onClientTick(PlayerTickEvent.Post event) {
        // Only process on client side
        if (event.getEntity().level().isClientSide()) {
            Minecraft client = Minecraft.getInstance();
            if (client.player != null) {
                ZoomManager.tick(client, ZOOM_KEYBIND);
            }
        }
    }
}
