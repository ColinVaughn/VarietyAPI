package org.varietymods.varietyapi.Items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.varietymods.varietyapi.Items.Custom.NetItem;
import org.varietymods.varietyapi.VarietyAPI;

public class ItemRegistry {

    public static final Item NETITEM = registerItem("net_item",
            new NetItem(new FabricItemSettings().group(ModItemGroup.VARIETY_MODS).maxCount(1)));
    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(VarietyAPI.MOD_ID, name), item);
    }


    public static void registerModItems() {
        VarietyAPI.LOGGER.debug("Registering Mod Items for " + VarietyAPI.MOD_ID);
    }
}
