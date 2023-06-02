package org.varietymods.varietyapi.Items.TooltipComponents;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import org.varietymods.varietyapi.mixin.accesors.EntityAccessor;

public class NetTooltipComponent implements TooltipComponent {

    private final NetTooltipData netData;
    private final MinecraftClient client;

    public NetTooltipComponent(NetTooltipData netData, MinecraftClient client) {
        this.netData = netData;
        this.client = client;
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 10;
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, MatrixStack matrices, ItemRenderer itemRenderer, int z) {
        World world = MinecraftClient.getInstance().world;
        Entity entity = netData.type().create(world);
        ((EntityAccessor) entity).setTouchingWater(true);
        EntityRenderDispatcher dispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();

        matrices.push();
        matrices.translate(2, 2, z);
        renderEntity(matrices, x, y, entity);
        matrices.pop();

        TooltipComponent.super.drawItems(textRenderer, x, y, matrices, itemRenderer, z);
    }

    protected void renderEntity(MatrixStack matrices, int x, int y, Entity entity) {
        float size = 24;
        matrices.push();
        matrices.translate(x + 10, y + 2, 1050);
        matrices.scale(1f, 1f, -1);
        matrices.translate(0, 0, 1000);
        matrices.scale(size, size, size);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180));

        float entityRotation = (client.world.getTime() + client.getTickDelta()) * 4;
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(entityRotation));

        EntityRenderDispatcher entityRenderDispatcher = this.client.getEntityRenderDispatcher();
        VertexConsumerProvider.Immediate immediate = this.client.getBufferBuilders().getEntityVertexConsumers();
        entityRenderDispatcher.render(entity, 0, 0, 0, 0.f, 1.f, matrices, immediate, LightmapTextureManager.MAX_LIGHT_COORDINATE);
        immediate.draw();

        matrices.pop();
    }
}
