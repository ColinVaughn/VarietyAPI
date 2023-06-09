package org.varietymods.varietyapi.API;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class GenericVariantModel<T extends LivingEntity & IVariantEntity & GeoAnimatable> extends GeoModel<T> {
    private final String modId;
    private final String modelResourcePath;
    private final String animationResourcePath;

    public GenericVariantModel(String modId,String modelResourcePath, String animationResourcePath) {
        this.modId = modId;

        this.modelResourcePath = modelResourcePath;
        this.animationResourcePath = animationResourcePath;
    }

    @Override
    public Identifier getModelResource(T object) {
        return new Identifier(modId,"geo/"+ modelResourcePath+".json");
    }

    @Override
    public Identifier getTextureResource(T object) {
        return object.getVariant().getTextureLocation();
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        return new Identifier(modId, "animations/"+animationResourcePath+".json");
    }
}
