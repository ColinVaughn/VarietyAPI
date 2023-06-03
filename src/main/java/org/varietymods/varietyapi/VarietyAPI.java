package org.varietymods.varietyapi;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.varietymods.varietyapi.Items.ItemRegistry;
import software.bernie.geckolib.GeckoLib;

public class VarietyAPI implements ModInitializer {
    public static final String MOD_ID = "varietyapi";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
    public static ItemGroup modGroup  = FabricItemGroup.builder(id(MOD_ID)).displayName(Text.translatable("varietyaquatic.test_group"))
            .icon(()-> new ItemStack(ItemRegistry.NETITEM))
            .build();
    @Override
    public void onInitialize() {

        GeckoLib.initialize();
        ItemRegistry.registerModItems();

    }
}
