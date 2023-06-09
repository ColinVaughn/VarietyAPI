package org.varietymods.varietyapi.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.varietymods.varietyapi.Items.ItemRegistry;

public class VarietyHudOverlay extends DrawableHelper implements HudRenderCallback {

    private final MinecraftClient client;

    public VarietyHudOverlay(MinecraftClient client) {
        this.client = client;
    }

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        if(MinecraftClient.getInstance().player != null) {
            ItemStack handStack = MinecraftClient.getInstance().player.getMainHandStack();

            if (handStack.isOf(ItemRegistry.NETITEM)) {
                NbtCompound compound = handStack.getOrCreateNbt();
                NbtCompound entityTag = compound.getCompound("pickedEntity");
                if(entityTag != null && entityTag.contains("id")) {
                    renderNettedEntity(matrixStack, tickDelta, Registries.ENTITY_TYPE.get(Identifier.tryParse(entityTag.getString("id"))));
                }
            }
        }
    }


    public void renderNettedEntity(MatrixStack matrixStack, float tickDelta, EntityType entityType) {
        if (entityType != null) {
            matrixStack.push();

            Entity entity = entityType.create(client.world);
            entity.setPos(client.player.getX(), client.player.getY(), client.player.getZ());

            int scale = 30;
            matrixStack.translate(30, 60, 0);
            matrixStack.scale((float) scale, (float) scale, -(float) scale);
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));

            float entityRotation = (client.world.getTime() + tickDelta) * 4;

            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entityRotation));

            EntityRenderDispatcher entityRenderDispatcher = client.getEntityRenderDispatcher();
            entityRenderDispatcher.setRenderShadows(false);
            VertexConsumerProvider.Immediate immediate = client.getBufferBuilders().getEntityVertexConsumers();

            RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(entity, 0, 0, 0, 0.0F, 1.0F, matrixStack, immediate, 0xF000F0));
            immediate.draw();
            entityRenderDispatcher.setRenderShadows(true);

            matrixStack.pop();
        }
    }


}
