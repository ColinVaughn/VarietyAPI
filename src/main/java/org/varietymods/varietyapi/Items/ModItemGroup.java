package org.varietymods.varietyapi.Items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.varietymods.varietyapi.VarietyAPI;

public class ItemGroup {
    public static final net.minecraft.item.ItemGroup VARIETY_AQUATIC = FabricItemGroupBuilder.build(
            new Identifier(VarietyAPI.MOD_ID, "variety_mods"), () -> new ItemStack(ItemRegistry.NETITEM));
}
