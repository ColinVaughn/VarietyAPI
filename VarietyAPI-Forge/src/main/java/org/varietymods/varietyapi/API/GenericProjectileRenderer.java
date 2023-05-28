package org.varietymods.varietyapi.API;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Projectile;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class GenericProjectileRenderer<T extends Projectile & IAnimatable> extends GeoProjectilesRenderer<T> {

    private final String modId;
    private final String texturePath;
    private final boolean isTranslucent;
    private final boolean hasGlowLayer;
    public GenericProjectileRenderer(EntityRendererProvider.Context ctx, AnimatedGeoModel<T> modelProvider, String modId, String texturePath
            , boolean isTranslucent, boolean hasGlowLayer) {
        super(ctx, modelProvider);
        this.modId = modId;
        this.texturePath = texturePath;
        this.isTranslucent = isTranslucent;
        this.hasGlowLayer = hasGlowLayer;


        this.shadowRadius = 0.4f;
        if(hasGlowLayer) {
            // this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureResource, getGeoModelProvider()::getModelResource, RenderLayer::getEntityTranslucentEmissive));
        }
    }
    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return new ResourceLocation(modId, "textures/entity/" + texturePath + ".png");
    }
    @Override
    public RenderType getRenderType(T animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder,
                                    int packedLightIn, ResourceLocation textureLocation) {
        return isTranslucent ? RenderType.armorCutoutNoCull(textureLocation)
                : super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
