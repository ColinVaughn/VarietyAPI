package org.varietymods.varietyapi.Items.Custom;

import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.varietymods.varietyapi.Items.TooltipComponents.NetTooltipData;


import java.util.List;
import java.util.Optional;

public class NetItem extends Item {
    public NetItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!world.isClient) {
            NbtCompound itemTag = stack.getOrCreateNbt();
            if (itemTag.contains("pickedEntity")) {
                // Spawn the stored entity
                NbtCompound entityTag = itemTag.getCompound("pickedEntity");
                EntityType<?> type = Registry.ENTITY_TYPE.get(Identifier.tryParse(entityTag.getString("id")));
                if (type != null) {
                    Entity entity = type.create(world);
                    if (entity != null) {
                        entity.readNbt(entityTag);
                        // Set the entity's position to the block that the player is looking at
                        HitResult hitResult = player.raycast(5.0, 1.0F, false);
                        if (hitResult.getType() == HitResult.Type.BLOCK) {
                            BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
                            pos = pos.up(1);
                            entity.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), player.getYaw(), player.getPitch());
                            world.spawnEntity(entity);
                            itemTag.remove("pickedEntity");
                            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
                        }
                    }
                }
            } else {
                // Pick up an entity
                Entity targetedEntity = rayTraceEntity(player, 5.0);
                if (targetedEntity != null) {
                    NbtCompound entityTag = new NbtCompound();
                    targetedEntity.saveNbt(entityTag);
                    itemTag.put("pickedEntity", entityTag);
                    targetedEntity.remove(Entity.RemovalReason.DISCARDED);
                    return new TypedActionResult<>(ActionResult.SUCCESS, stack);
                }
            }
        }
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }



    private Entity rayTraceEntity(PlayerEntity player, double maxDistance) {
        Vec3d startVec = player.getCameraPosVec(1.0F);
        Vec3d lookVec = player.getRotationVec(1.0F);
        Vec3d endVec = startVec.add(lookVec.x * maxDistance, lookVec.y * maxDistance, lookVec.z * maxDistance);
        Box box = new Box(startVec, endVec);
        List<Entity> entities = player.world.getOtherEntities(player, box);
        Entity nearestEntity = null;
        double nearestDistanceSq = maxDistance * maxDistance;

        for (Entity entity : entities) {
            Box entityBox = entity.getBoundingBox();
            if (entityBox.getXLength() <= 1.0 && entityBox.getYLength() <= 1.0 && entityBox.getZLength() <= 1.0) {
                if (!(entity.getType() == EntityType.ENDER_DRAGON || entity.getType().getSpawnGroup().asString() == SpawnGroup.MISC.asString())) {
                    double distanceSq = player.getPos().squaredDistanceTo(entity.getPos());
                    if (distanceSq < nearestDistanceSq) {
                        nearestEntity = entity;
                        nearestDistanceSq = distanceSq;
                        if (nearestDistanceSq < 1.0) {
                            break;
                        }
                    }
                }
            }
        }

        return nearestEntity;
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        NbtCompound itemTag = stack.getOrCreateNbt();
        NbtCompound entityTag = itemTag.getCompound("pickedEntity");
        EntityType<?> type = Registry.ENTITY_TYPE.get(Identifier.tryParse(entityTag.getString("id")));

        return Optional.of(new NetTooltipData(type));
    }
}