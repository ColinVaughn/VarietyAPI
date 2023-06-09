package org.varietymods.varietyapi.API;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericProjectileModel<T extends ProjectileEntity & IAnimatable> extends AnimatedGeoModel<T> {
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
    public Identifier getModelResource(T object) {
        return new Identifier(modId, "geo/"+ modelPath+".json");
    }

    @Override
    public Identifier getTextureResource(T object) {
        return new Identifier(modId, "textures/entity/"+texturePath+".png");
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        if ("null".equals(animationPath)) {
            return null;
        }
        return new Identifier(modId,"animations/"+animationPath+".json");
    }
}
