package org.varietymods.varietyapi.mixin.accesors;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Accessor("touchingWater")
    void setTouchingWater(boolean inWater);
}
