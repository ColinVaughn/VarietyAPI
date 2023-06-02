package org.varietymods.varietyapi.Item.Custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class NetItem extends Item {

    public NetItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            CompoundTag itemTag = stack.getOrCreateTag();
            if (itemTag.contains("pickedEntity")) {
                // Spawn the stored entity
                CompoundTag entityTag = itemTag.getCompound("pickedEntity");
                EntityType<?> type = Registry.ENTITY_TYPE.get(ResourceLocation.tryParse(entityTag.getString("id")));
                if (type != null) {
                    Entity entity = type.create(world);
                    if (entity != null) {
                        entity.load(entityTag);
                        // Set the entity's position to the block that the player is looking at
                        HitResult hitResult = player.pick(5.0, 1.0F, false);
                        if (hitResult.getType() == HitResult.Type.BLOCK) {
                            BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
                            pos = pos.above(1);
                            entity.moveTo(pos.getX(), pos.getY(), pos.getZ(), player.getYRot(), player.getXRot());
                            world.addFreshEntity(entity);
                            itemTag.remove("pickedEntity");
                            return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
                        }
                    }
                }
            } else {
                // Pick up an entity
                Entity targetedEntity = rayTraceEntity(player, 5.0);
                if (targetedEntity != null) {
                    CompoundTag entityTag = new CompoundTag();
                    targetedEntity.save(entityTag);
                    itemTag.put("pickedEntity", entityTag);
                    targetedEntity.remove(Entity.RemovalReason.DISCARDED);
                    return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
                }
            }
        }
        return new InteractionResultHolder<>(InteractionResult.PASS, stack);
    }



    private Entity rayTraceEntity(Player player, double maxDistance) {
        Vec3 startVec = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getViewVector(1.0F);
        Vec3 endVec = startVec.add(lookVec.x * maxDistance, lookVec.y * maxDistance, lookVec.z * maxDistance);
        AABB box = new AABB(startVec, endVec);
        List<Entity> entities = player.level.getEntities(player, box);
        Entity nearestEntity = null;
        double nearestDistanceSq = maxDistance * maxDistance;

        for (Entity entity : entities) {
            AABB entityBox = entity.getBoundingBox();
            if (entityBox.getXsize() <= 1.0 && entityBox.getYsize() <= 1.0 && entityBox.getZsize() <= 1.0) {
                if (!(entity.getType() == EntityType.ENDER_DRAGON || entity.getType().getCategory() == MobCategory.MISC)) {
                    double distanceSq = player.getPosition(1f).distanceToSqr(entity.getPosition(1f));
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



}