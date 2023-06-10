package org.varietymods.varietyapi.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.MinecraftClient;

public class VarietyAPIClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new VarietyHudOverlay(MinecraftClient.getInstance()));
        TooltipComponentCallback.EVENT.register(new VarietyTooltipCallback());
    }
}
