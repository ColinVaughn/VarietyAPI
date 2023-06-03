package org.varietymods.varietyapi.API;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import java.util.function.Function;

public class GenericBlockRenderer<T extends BlockEntity & GeoAnimatable> extends GeoBlockRenderer<T> {
    private final GeoModel model;
    private final Function<T, Identifier> textureGetter;

    public GenericBlockRenderer(GeoModel model, Function<T, Identifier> textureGetter) {
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
