package org.varietymods.varietyapi.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexConsumerProvider;

public class VarietyAPIClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new VarietyHudOverlay(MinecraftClient.getInstance(),VertexConsumerProvider.immediate(new BufferBuilder(10))));
        TooltipComponentCallback.EVENT.register(new VarietyTooltipCallback());
    }
}
