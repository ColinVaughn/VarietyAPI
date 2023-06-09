package org.varietymods.varietyapi;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.varietymods.varietyapi.Entities.LeviathanEntity;
import org.varietymods.varietyapi.Entities.ModEntities;
import org.varietymods.varietyapi.Items.ItemRegistry;
import org.varietymods.varietyapi.Items.ModItemGroup;
import software.bernie.geckolib.GeckoLib;

public class VarietyAPI implements ModInitializer {
    public static final String MOD_ID = "varietyapi";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
    @Override
    public void onInitialize() {
        ModItemGroup.registerItemGroups();
        GeckoLib.initialize();
        ItemRegistry.registerModItems();

        FabricDefaultAttributeRegistry.register(ModEntities.LEVIATHAN, LeviathanEntity.setAttributes());

    }
}
