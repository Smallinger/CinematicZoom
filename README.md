# CinematicZoom Port for (NeoForge 1.21.10)

<div align="center">

<img src="https://github.com/Smallinger/CinematicZoom/blob/main/src/main/resources/assets/cinematiczoom/icon.png" alt="CinematicZoom Icon" width="300">

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.10-green.svg)](https://www.minecraft.net/)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.10.49-orange.svg)](https://neoforged.net/)
[![Java](https://img.shields.io/badge/Java-21-red.svg)](https://www.oracle.com/java/)
[![Build Status](https://github.com/smallinger/CinematicZoom/workflows/Build%20and%20Release/badge.svg)](https://github.com/smallinger/CinematicZoom/actions)

A client-side Minecraft mod that adds smooth cinematic zoom with black bars.

[Features](#features) • [Installation](#installation) • [Configuration](#configuration) • [Building](#building) • [Credits](#credits)

</div>

---

## ✨ Features

- **🎬 Cinematic Zoom**: Press `C` (default) to activate smooth zoom with letterbox effect
- **📏 Black Bars**: Configurable cinematic black bars for that movie feeling
- **🖱️ Mouse Wheel Control**: Adjust zoom level dynamically while zooming
- **⚙️ Highly Configurable**: All settings accessible via NeoForge config screen
- **👻 Auto HUD Hide**: Optionally hides HUD and crosshair during zoom
- **🎥 Smooth Camera**: Optionally enables cinematic camera movement
- **🚀 Client-Side Only**: Works on any server, no server-side installation needed

## 🎬 Demo

<div align="center">

![CinematicZoom Demo](.github/media/demo.gif)

*Press C to activate cinematic zoom with smooth black bars*

</div>

## 📦 Installation

1. Install [NeoForge](https://neoforged.net/) 21.10.49 or higher for Minecraft 1.21.10
2. Download the latest release from [Releases](https://github.com/smallinger/CinematicZoom/releases)
3. Place the `.jar` file in your `mods` folder
4. Launch Minecraft and enjoy!

## ⚙️ Configuration

Access the configuration through:
- **Mods Menu** → **CinematicZoom** → **Config**

### Available Settings

| Setting | Description | Default |
|---------|-------------|---------|
| **Black Bars Height** | Height of cinematic bars (0-50%) | 15% |
| **Smoothing Speed** | Animation transition time in ms (0 = instant) | 240ms |
| **Mouse Wheel Enabled** | Allow zoom adjustment with mouse wheel | ✓ |
| **Hide HUD During Zoom** | Hide HUD and crosshair while zooming | ✓ |
| **Cinematic Camera** | Enable smooth camera movement | ✓ |
| **Base Zoom Multiplier** | Initial zoom level | 0.33 |
| **Min/Max Zoom** | Zoom range limits | 0.10 - 1.00 |
| **Wheel Step** | Mouse wheel sensitivity | 0.05 |

## 🎮 Controls

- **Zoom Key**: `C` (configurable in Minecraft controls)
- **Mouse Wheel**: Adjust zoom level while holding zoom key

## 🛠️ Building from Source

```bash
git clone https://github.com/smallinger/CinematicZoom.git
cd CinematicZoom/NeoForge
./gradlew build
```

The compiled `.jar` will be in `build/libs/`

## 🎯 Technical Details

### Architecture

```
de.smallinger.cinematiczoom/
├── CinematicZoom.java          # Main mod initialization
├── CinematicZoomClient.java    # Client-side setup & key bindings
├── Config.java                 # NeoForge config system
├── ZoomManager.java            # Core zoom logic & rendering
└── mixin/
    ├── GameRendererMixin.java  # FOV modification injection
    ├── MouseMixin.java         # Mouse wheel event handling
    └── InGameHudMixin.java     # Black bars overlay rendering
```

### Key Changes from Fabric

- **Config System**: Migrated from JSON to NeoForge's ModConfigSpec (TOML)
- **Key Bindings**: Uses NeoForge's `RegisterKeyMappingsEvent`
- **Events**: PlayerTickEvent.Post instead of Fabric's ClientTickEvents
- **API Mappings**: Updated for NeoForge method names and classes

## 📜 Credits

- **Original Mod**: [Mel1x](https://github.com/mel1x/CinematicZoom) - Fabric version
- **NeoForge Port**: [Smallinger](https://github.com/smallinger/CinematicZoom)
- **License**: MIT

### Original Fabric Version

This is a port of the original [CinematicZoom for Fabric](https://github.com/mel1x/CinematicZoom).  
For the Fabric version, visit the original repository.

## 🐛 Issues & Support

Found a bug or have a suggestion?
- **NeoForge Port Issues**: [Open an issue](https://github.com/smallinger/CinematicZoom/issues)
- **Original Fabric Version**: [Visit original repo](https://github.com/mel1x/CinematicZoom/issues)

## 💖 Support My Work

If you like what I do, consider supporting me:

[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/smallinger)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">

Made with ❤️ for the Minecraft community

</div>
