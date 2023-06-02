package org.varietymods.varietyapi.Client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import org.varietymods.varietyapi.Item.ModItems;

public class VarietyHudOverlay extends GuiComponent {

    private final Minecraft client;

    public VarietyHudOverlay(Minecraft client) {
        this.client = client;
    }

    public void onRenderHud(PoseStack matrixStack, float tickDelta) {
        if(Minecraft.getInstance().player != null) {
            ItemStack handStack = Minecraft.getInstance().player.getMainHandItem();

            if (handStack.getItem() == ModItems.NET_ITEM.get()) {
                CompoundTag compound = handStack.getOrCreateTag();
                CompoundTag entityTag = compound.getCompound("pickedEntity");
                if(entityTag != null && entityTag.contains("id")) {
                    renderNettedEntity(matrixStack, tickDelta, Registry.ENTITY_TYPE.get(ResourceLocation.tryParse(entityTag.getString("id"))));
                }
            }
        }
    }


    public void renderNettedEntity(PoseStack matrixStack, float tickDelta, EntityType entityType) {
        if (entityType != null) {
            matrixStack.pushPose();

            Entity entity = entityType.create(client.level);
            entity.setPos(client.player.getX(), client.player.getY(), client.player.getZ());

            int scale = 30;
            matrixStack.translate(30, 60, 0);
            matrixStack.scale((float) scale, (float) scale, -(float) scale);
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(180));

            float entityRotation = (client.level.getTimeOfDay(1) + tickDelta) * 4;

            matrixStack.mulPose(Vector3f.XP.rotationDegrees(entityRotation));

            EntityRenderDispatcher entityRenderDispatcher = client.getEntityRenderDispatcher();
                entityRenderDispatcher.setRenderShadow(false);
            BufferBuilder bufferBuilder = new BufferBuilder(128);

            RenderSystem.setShaderFogStart(0.0F);
            RenderSystem.setShaderFogEnd(1.0F);
            entityRenderDispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixStack, (MultiBufferSource) bufferBuilder, 0xF000F0);
            bufferBuilder.end();


            entityRenderDispatcher.setRenderShadow(true);

            matrixStack.popPose();
        }
    }


}
