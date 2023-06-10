package org.varietymods.varietyapi.Items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.varietymods.varietyapi.VarietyAPI;

public class ModItemGroup {
    public static ItemGroup VARIETY_MODS = Registry.register(Registries.ITEM_GROUP, new Identifier(VarietyAPI.MOD_ID, "variety_mods"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.variety_mods"))
                    .icon(() -> new ItemStack(ItemRegistry.NETITEM)).entries((displayContext, entries) -> {
                        entries.add(ItemRegistry.NETITEM);
                    }).build());

    public static void registerItemGroups() {
    }
}