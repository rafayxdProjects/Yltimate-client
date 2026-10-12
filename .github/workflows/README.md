# Yltimate Client (starter)

A lightweight Fabric client-side mod starter for Minecraft Java 1.21.11.

## Included
- Compact Yltimate HUD
- FPS display
- Left-click CPS counter
- WASD and mouse-button indicators
- Equipped armor-piece count

## Build
1. Install JDK 21 and a recent Gradle installation.
2. Open this folder in IntelliJ IDEA with the Minecraft Development plugin, or run `gradle build`.
3. Copy `build/libs/yltimate-client-0.1.0.jar` into `.minecraft/mods` alongside Fabric API.

This is a starter mod, not a complete standalone launcher/client yet. It does not include Sodium-style renderer optimizations; those should be added as compatible performance mods or implemented separately. Test on a clean profile before distributing.
