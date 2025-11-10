package de.smallinger.cinematiczoom;

import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/**
 * Factory for creating the configuration screen for CinematicZoom.
 * This integrates with NeoForge's built-in configuration GUI system.
 */
public class ConfigScreenFactory implements IConfigScreenFactory {
    
    @Override
    public Screen createScreen(ModContainer container, Screen lastScreen) {
        return new ConfigurationScreen(container, lastScreen);
    }
}
