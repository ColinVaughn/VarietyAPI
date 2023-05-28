package org.varietymods.varietyapi.Item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreative {
    public static final CreativeModeTab VARIETY_API_TAB = new CreativeModeTab("variety_api") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.NETHERITE_INGOT);
        }
    };
}
