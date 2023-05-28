package org.varietymods.varietyapi.Item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.varietymods.varietyapi.VarietyAPI;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VarietyAPI.MODID);
    /*

    public static final RegistryObject<Item> NET_ITEM = ITEMS.register("net_item",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_API_TAB)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

     */
}
