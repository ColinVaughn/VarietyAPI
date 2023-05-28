package org.varietymods.varietyapi.API;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericVariantModel<T extends LivingEntity & IVariantEntity & IAnimatable> extends AnimatedGeoModel<T> {
    private final String modId;
    private final String modelResourcePath;
    private final String animationResourcePath;

    public GenericVariantModel(String modId,String modelResourcePath, String animationResourcePath) {
        this.modId = modId;

        this.modelResourcePath = modelResourcePath;
        this.animationResourcePath = animationResourcePath;
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return new ResourceLocation(modId,"geo/"+ modelResourcePath+".json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return object.getVariant().getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return new ResourceLocation(modId, "animations/"+animationResourcePath+".json");
    }
}
