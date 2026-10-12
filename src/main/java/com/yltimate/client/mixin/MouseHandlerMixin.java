package com.yltimate.client.mixin;

import com.yltimate.client.MouseClicks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public abstract class MouseHandlerMixin {
    @Inject(method = "onPress", at = @At("HEAD"))
    private void yltimate$trackClicks(long window, int button, int action, int mods, CallbackInfo ci) {
        // GLFW_PRESS is 1. Only count clicks while no menu is open.
        if (button == 0 && action == 1 && Minecraft.getInstance().screen == null) MouseClicks.registerLeftClick();
    }
}
