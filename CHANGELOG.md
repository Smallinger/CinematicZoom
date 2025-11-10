# Changelog

All notable changes to the CinematicZoom NeoForge port will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2025-11-10

### Added
- Initial NeoForge port of CinematicZoom for Minecraft 1.21.10
- Smooth cinematic zoom with customizable black bars
- Mouse wheel control for dynamic zoom adjustment
- Configurable settings via NeoForge config screen
- Optional HUD hiding during zoom
- Optional cinematic camera mode during zoom
- Full compatibility with NeoForge 21.10.49+

### Changed
- Ported from Fabric to NeoForge mod loader
- Migrated config system from JSON to NeoForge's ModConfigSpec (TOML)
- Updated keybinding registration to use NeoForge's RegisterKeyMappingsEvent
- Updated client tick handling to use PlayerTickEvent.Post
- Updated all Minecraft API calls to NeoForge mappings

### Technical Details
- Package: `de.smallinger.cinematiczoom`
- Mixins: GameRendererMixin, MouseMixin, InGameHudMixin
- Config type: CLIENT (stored in config/cinematiczoom-client.toml)
- Requires Java 21

---

## Original Fabric Version

This is a port of the original [CinematicZoom for Fabric](https://github.com/mel1x/CinematicZoom) by Mel1x.
For the Fabric version changelog, please visit the original repository.

## Credits

- **Original Mod**: Mel1x - [CinematicZoom for Fabric](https://github.com/mel1x/CinematicZoom)
- **NeoForge Port**: smallinger
- **License**: MIT

[1.0.0]: https://github.com/smallinger/CinematicZoom/releases/tag/v1.0.0
