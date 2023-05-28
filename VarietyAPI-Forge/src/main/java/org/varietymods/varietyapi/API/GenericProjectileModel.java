package org.varietymods.varietyapi.API;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Projectile;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericProjectileModel<T extends Projectile & IAnimatable> extends AnimatedGeoModel<T> {
    private final String modId;
    private final String modelPath;
    private final String texturePath;
    private final String animationPath;

    public GenericProjectileModel(String modId, String modelPath, String texturePath, String animationPath) {
        this.modId = modId;
        this.modelPath = modelPath;
        this.texturePath = texturePath;
        this.animationPath = animationPath;
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return new ResourceLocation(modId, "geo/"+ modelPath+".json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return new ResourceLocation(modId, "textures/entity/"+texturePath+".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        if ("null".equals(animationPath)) {
            return null;
        }
        return new ResourceLocation(modId,"animations/"+animationPath+".json");
    }
}
