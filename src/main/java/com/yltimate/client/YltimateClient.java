package com.yltimate.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;

public final class YltimateClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudElementRegistry.addLast(Identifier.fromNamespaceAndPath("yltimate", "pvp_hud"), YltimateClient::renderHud);
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hit) -> {
            if (world.isClientSide()) Combo.hit();
            return InteractionResult.PASS;
        });
    }

    private static void renderHud(GuiGraphics g, net.minecraft.client.DeltaTracker tickCounter) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        int x = 8, y = 8;
        int panel = 0xB9101420;
        int cyan = 0xFF57E6FF;
        int white = 0xFFF2F6FF;
        g.fill(x - 4, y - 4, x + 132, y + 95, panel);
        g.fill(x - 4, y - 4, x + 132, y - 2, cyan);
        g.drawString(mc.font, "YLTIMATE", x, y, cyan, false);
        g.drawString(mc.font, "FPS  " + mc.getFps(), x, y + 13, white, false);
        g.drawString(mc.font, "CPS  " + MouseClicks.getCps(), x, y + 25, white, false);
        g.drawString(mc.font, "W " + key(mc.options.keyUp.isDown()) + "  A " + key(mc.options.keyLeft.isDown())
                + "  S " + key(mc.options.keyDown.isDown()) + "  D " + key(mc.options.keyRight.isDown()), x, y + 37, white, false);
        g.drawString(mc.font, "LMB " + key(mc.options.keyAttack.isDown()) + "  RMB " + key(mc.options.keyUse.isDown()), x, y + 49, white, false);

        var player = mc.player;
        String armor = "ARMOR  " + countArmor(player.getItemBySlot(EquipmentSlot.HEAD).isEmpty(),
                player.getItemBySlot(EquipmentSlot.CHEST).isEmpty(),
                player.getItemBySlot(EquipmentSlot.LEGS).isEmpty(),
                player.getItemBySlot(EquipmentSlot.FEET).isEmpty());
        g.drawString(mc.font, armor, x, y + 61, white, false);
        Combo.tick(mc.player.hurtTime > 0);
        g.drawString(mc.font, "COMBO  " + Combo.get(), x, y + 73, white, false);
        g.drawString(mc.font, "Fabric 1.21.11", x, y + 85, 0xFF9AA8BD, false);
    }

    private static String key(boolean down) { return down ? "■" : "□"; }
    private static int countArmor(boolean head, boolean chest, boolean legs, boolean feet) {
        return (head ? 0 : 1) + (chest ? 0 : 1) + (legs ? 0 : 1) + (feet ? 0 : 1);
    }
}
