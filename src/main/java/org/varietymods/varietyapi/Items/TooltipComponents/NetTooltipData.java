package org.varietymods.varietyapi.Items.TooltipComponents;

import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.EntityType;

public record NetTooltipData(EntityType<?> type) implements TooltipData {

}