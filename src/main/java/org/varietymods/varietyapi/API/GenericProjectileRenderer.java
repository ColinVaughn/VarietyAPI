package org.varietymods.varietyapi.API;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class GenericProjectileRenderer<T extends ProjectileEntity & IAnimatable> extends GeoProjectilesRenderer<T> {

    private final String modId;
    private final String texturePath;
    private final boolean isTranslucent;
    private final boolean hasGlowLayer;
    public GenericProjectileRenderer(EntityRendererFactory.Context ctx, AnimatedGeoModel<T> modelProvider, String modId, String texturePath
                                  , boolean isTranslucent, boolean hasGlowLayer) {
        super(ctx, modelProvider);
        this.modId = modId;
        this.texturePath = texturePath;
        this.isTranslucent = isTranslucent;
        this.hasGlowLayer = hasGlowLayer;
        this.shadowRadius = 0.4f;
    }
    @Override
    public Identifier getTextureLocation(T instance) {
        return new Identifier(modId, "textures/entity/" + texturePath + ".png");
    }
    @Override
    public RenderLayer getRenderType(T animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {

        return isTranslucent ? RenderLayer.getEntityTranslucent(getTextureLocation(animatable))
                : super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
