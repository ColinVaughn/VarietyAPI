package org.varietymods.varietyapi.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import org.varietymods.varietyapi.API.GenericModel;
import org.varietymods.varietyapi.API.GenericRenderer;
import org.varietymods.varietyapi.Entities.ModEntities;
import org.varietymods.varietyapi.VarietyAPI;

public class VarietyAPIClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new VarietyHudOverlay(MinecraftClient.getInstance()));
        TooltipComponentCallback.EVENT.register(new VarietyTooltipCallback());
        EntityRendererRegistry.register(ModEntities.LEVIATHAN, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(VarietyAPI.MOD_ID,"spermwhale.geo","spermwhale_texture","spermwhale.animation"),VarietyAPI.MOD_ID, "spermwhale_texture", 1.1f,1.2f, false,false)
        );
    }
}
