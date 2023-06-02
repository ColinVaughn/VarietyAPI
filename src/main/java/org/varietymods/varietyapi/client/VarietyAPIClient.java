package org.varietymods.varietyapi.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.FluidRenderer;

public class VarietyAPIClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new VarietyHudOverlay(MinecraftClient.getInstance()));

    }
}
