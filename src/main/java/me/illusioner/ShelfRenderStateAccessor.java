package me.illusioner;

import net.minecraft.world.level.block.entity.ShelfBlockEntity;

public interface ShelfRenderStateAccessor {
    ShelfBlockEntity getBlockEntity();
    void setBlockEntity(ShelfBlockEntity blockEntity);
}