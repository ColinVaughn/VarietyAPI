package org.varietymods.varietyapi.client;

import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.TooltipData;
import org.jetbrains.annotations.Nullable;
import org.varietymods.varietyapi.Items.TooltipComponents.NetTooltipComponent;
import org.varietymods.varietyapi.Items.TooltipComponents.NetTooltipData;

public class VarietyTooltipCallback implements TooltipComponentCallback {

    @Override
    public @Nullable TooltipComponent getComponent(TooltipData data) {
        if(data instanceof NetTooltipData netData) {
            return new NetTooltipComponent(netData, MinecraftClient.getInstance());
        }
        return null;
    }

}