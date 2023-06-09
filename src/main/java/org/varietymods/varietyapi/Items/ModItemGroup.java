package org.varietymods.varietyapi.Items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.varietymods.varietyapi.VarietyAPI;

public class ModItemGroup {
    public static ItemGroup VARIETY_MODS;

    public static void registerItemGroups() {

        VARIETY_MODS = FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.variety_mods"))
                .icon(() -> new ItemStack(ItemRegistry.NETITEM)).build();
    }
}
