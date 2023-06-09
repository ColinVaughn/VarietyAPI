package org.varietymods.varietyapi.Entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.varietymods.varietyapi.VarietyAPI;

public class ModEntities {
    public static final EntityType<LeviathanEntity> LEVIATHAN = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(VarietyAPI.MOD_ID, "leviathan"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, LeviathanEntity::new)
                    .dimensions(EntityDimensions.fixed(4f, 3f)).trackRangeBlocks(50).build());
}
