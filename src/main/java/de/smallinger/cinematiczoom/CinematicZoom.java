package de.smallinger.cinematiczoom;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

/**
 * CinematicZoom - A client-side zoom mod with cinematic black bars.
 * Ported from Fabric to NeoForge by smallinger.
 * Original mod by Mel1x.
 */
@Mod(CinematicZoom.MODID)
public class CinematicZoom {
    public static final String MODID = "cinematiczoom";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CinematicZoom(IEventBus modEventBus, ModContainer modContainer) {
        // Register client config (this is a client-side only mod)
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        
        LOGGER.info("CinematicZoom initialized - Client-side zoom mod");
    }
}
