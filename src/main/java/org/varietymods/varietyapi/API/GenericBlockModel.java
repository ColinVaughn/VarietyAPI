package org.varietymods.varietyapi.API;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class GenericBlockModel<T extends BlockEntity & GeoAnimatable> extends GeoModel<T> {
    private String animationFile;
    private String modelFile;
    private String textureFile;
    private String modid;


    public GenericBlockModel(String modId,String animationFile, String modelFile, String textureFile) {
        this.animationFile = animationFile;
        this.modelFile = modelFile;
        this.textureFile = textureFile;
        this.modid = modId;

    }

    @Override
    public Identifier getAnimationResource(T entity) {
        return new Identifier(modid, "animations/"+animationFile+".json");
    }

    @Override
    public Identifier getModelResource(T animatable) {
        return new Identifier(modid, "geo/"+modelFile);
    }

    @Override
    public Identifier getTextureResource(T entity) {
        return new Identifier(modid,"textures/block/"+textureFile+".png");
    }
}
