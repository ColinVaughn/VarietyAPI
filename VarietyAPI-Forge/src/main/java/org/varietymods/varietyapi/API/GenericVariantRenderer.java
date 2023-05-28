package org.varietymods.varietyapi.API;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GenericVariantRenderer<T extends LivingEntity & IVariantEntity & IAnimatable, V extends Enum<V> & IVariant> extends GeoEntityRenderer<T> {
    private final float scale;
    private final float scaleBaby;
    private final boolean isTranslucent;
    private final boolean hasGlowLayer;
    public GenericVariantRenderer(EntityRendererProvider.Context ctx, AnimatedGeoModel<T> model, float scale, float scaleBaby, boolean isTranslucent, boolean hasGlowLayer) {
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
    public ResourceLocation getTextureLocation(T instance) {
        return instance.getVariant().getTextureResource();
    }

    @Override
    public RenderType getRenderType(T animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder,
                                    int packedLightIn, ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(scaleBaby, scaleBaby, scaleBaby);
        } else {
            stack.scale(scale, scale, scale);
        }
        return isTranslucent ? RenderType.armorCutoutNoCull(textureLocation)
                : super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
