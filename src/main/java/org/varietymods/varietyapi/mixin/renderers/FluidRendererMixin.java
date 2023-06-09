package org.varietymods.varietyapi.mixin.renderers;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(FluidRenderer.class)
public class FluidRendererMixin {

    @Inject(method = "render", at = @At("HEAD"))
    public void renderInject(BlockRenderView world, BlockPos pos, VertexConsumer vertexConsumer, BlockState blockState, FluidState fluidState, CallbackInfo ci) {
        //vertexConsumer.color(pos.getX(), pos.getY(), pos.getZ(), 255);
    }

    @Inject(method = "getLight", at = @At("HEAD"), cancellable = true)
    public void getLightInject(BlockRenderView world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {

    }

}