package org.varietymods.varietyapi.Item.Custom;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
/*
public class NetItem extends Item {

    public NetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            CompoundTag itemTag = stack.getOrCreateTag();
            if (itemTag.contains("pickedEntity")) {
                // Spawn the stored entity
                CompoundTag entityTag = itemTag.getCompound("pickedEntity");
                EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(entityTag.getString("id")));
                if (type != null) {
                    Entity entity = type.create(world);
                    if (entity != null) {
                        entity.read(entityTag);
                        entity.setLocationAndAngles(player.getX(), player.getY(), player.getZ(), player.yHeadRot, player.yHeadRotO);
                        world.addEntity(entity);
                        itemTag.remove("pickedEntity");
                        return InteractionResult.SUCCESS;
                    }
                }
            } else {
                // Pick up an entity
                Entity targetedEntity = rayTraceEntity(player, 5.0);
                if (targetedEntity != null) {
                    CompoundTag entityTag = new CompoundTag();
                    targetedEntity.save(entityTag);
                    itemTag.put("pickedEntity", entityTag);
                    targetedEntity.remove();
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }

    private Entity rayTraceEntity(Player player, double maxDistance) {
        Vec3d startVec = player.getPositionVec();
        Vec3d lookVec = player.getLookVec();
        Vec3d endVec = startVec.add(lookVec.x * maxDistance, lookVec.y * maxDistance, lookVec.z * maxDistance);
        AxisAlignedBB box = new AxisAlignedBB(startVec, endVec);
        List<Entity> entities = player.level.getEntitiesWithinAABBExcludingEntity(player, box);
        Entity nearestEntity = null;
        double nearestDistanceSq = maxDistance * maxDistance;

        for (Entity entity : entities) {
            AxisAlignedBB entityBox = entity.getBoundingBox();
            if (entityBox.getXSize() <= 1.0 && entityBox.getYSize() <= 1.0 && entityBox.getZSize() <= 1.0) {
                double distanceSq = player.getPositionVec().squareDistanceTo(entity.getPositionVec());
                if (distanceSq < nearestDistanceSq) {
                    nearestEntity = entity;
                    nearestDistanceSq = distanceSq;
                    if (nearestDistanceSq < 1.0) {
                        break;
                    }
                }
            }
        }

        return nearestEntity;
    }
    }
    */
