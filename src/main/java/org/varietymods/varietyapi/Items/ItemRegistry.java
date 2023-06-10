package org.varietymods.varietyapi.Items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.varietymods.varietyapi.Items.Custom.NetItem;
import org.varietymods.varietyapi.VarietyAPI;

public class ItemRegistry {

    public static final Item NETITEM = registerItem("net_item",
            new NetItem(new FabricItemSettings()));
    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(VarietyAPI.MOD_ID, name), item);
    }
    public static void addItemsToItemGroup() {
        addToItemGroup(ModItemGroup.VARIETY_MODS, NETITEM);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        VarietyAPI.LOGGER.debug("Registering Mod Items for " + VarietyAPI.MOD_ID);
        addItemsToItemGroup();
    }
}
