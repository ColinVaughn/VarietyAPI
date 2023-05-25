package org.varietymods.varietyapi.API;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import java.util.function.Function;

public class GenericBlockRenderer<T extends BlockEntity& IAnimatable> extends GeoBlockRenderer<T> {
    private final AnimatedGeoModel model;
    private final Function<T, Identifier> textureGetter;

    public GenericBlockRenderer(AnimatedGeoModel model, Function<T, Identifier> textureGetter) {
        super(model);
        this.model = model;
        this.textureGetter = textureGetter;
    }

    @Override
    public RenderLayer getRenderType(T animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(textureGetter.apply(animatable));
    }
}
