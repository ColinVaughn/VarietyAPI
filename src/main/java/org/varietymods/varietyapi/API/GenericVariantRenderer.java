package org.varietymods.varietyapi.API;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GenericVariantRenderer<T extends LivingEntity & IVariantEntity & GeoAnimatable, V extends Enum<V> & IVariant> extends GeoEntityRenderer<T> {
    private final float scale;
    private final float scaleBaby;
    private final boolean isTranslucent;
    private final boolean hasGlowLayer;
    public GenericVariantRenderer(EntityRendererFactory.Context ctx, GeoModel<T> model, float scale, float scaleBaby, boolean isTranslucent, boolean hasGlowLayer) {
        super(ctx, model);
        this.scale = scale;
        this.scaleBaby = scaleBaby;
        this.isTranslucent = isTranslucent;
        this.hasGlowLayer = hasGlowLayer;
        this.shadowRadius = 0.4f;
        if(hasGlowLayer) {
            // this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureResource, getGeoModelProvider()::getModelResource, RenderLayer::getEntityTranslucentEmissive));
        }
    }

    @Override
    public Identifier getTextureLocation(T instance) {
        return instance.getVariant().getTextureLocation();
    }

    @Override
    public void render(T animatable,float entityYaw, float partialTick, MatrixStack poseStack,
                                     VertexConsumerProvider bufferSource, int packedLight) {
        if(animatable.isBaby()) {
            poseStack.scale(scaleBaby, scaleBaby, scaleBaby);
        } else {
            poseStack.scale(scale, scale, scale);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
