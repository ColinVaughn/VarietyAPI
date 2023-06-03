package org.varietymods.varietyapi.API;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class GenericModel<T extends LivingEntity & GeoAnimatable> extends GeoModel<T> {
    private final String modId;
    private final String modelPath;
    private final String texturePath;
    private final String animationPath;

    public GenericModel(String modId, String modelPath, String texturePath, String animationPath) {
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
        return new Identifier(modId,"animations/"+animationPath+".json");
    }
}
