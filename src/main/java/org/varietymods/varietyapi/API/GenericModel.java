package org.varietymods.varietyapi.API;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericModel<T extends LivingEntity & IAnimatable> extends AnimatedGeoModel<T> {
    private final String modId;
    private final String modelPath;
    private final String texturePath;
    private final String animationPath;
    private final boolean hasaltchild;


    public GenericModel(String modId, String modelPath, String texturePath, String animationPath, boolean hasaltchild) {
        this.modId = modId;
        this.modelPath = modelPath;
        this.texturePath = texturePath;
        this.animationPath = animationPath;
        this.hasaltchild = hasaltchild;

    }

    @Override
    public Identifier getModelResource(T object) {
        if (hasaltchild&&object.isBaby()){
            return new Identifier(modId, "geo/"+ modelPath+"_child.json");
        }

        return new Identifier(modId, "geo/" + modelPath + ".json");

    }

    @Override
    public Identifier getTextureResource(T object) {
        if (hasaltchild&&object.isBaby()) {
            return new Identifier(modId, "textures/entity/" + texturePath + "_child.png");
        }
        return new Identifier(modId, "textures/entity/" + texturePath + ".png");
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        if (hasaltchild&&animatable.isBaby()) {
            return new Identifier(modId,"animations/"+animationPath+"_child.json");

        }
        return new Identifier(modId,"animations/"+animationPath+".json");
    }
}
