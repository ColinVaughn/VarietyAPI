package org.varietymods.varietyapi;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.varietymods.varietyapi.Items.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;

public class VarietyAPI implements ModInitializer {
    public static final String MOD_ID = "varietyapi";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        ItemRegistry.registerModItems();

    }
}
