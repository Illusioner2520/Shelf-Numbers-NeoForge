package me.illusioner.mixin;

import net.minecraft.client.renderer.blockentity.state.ShelfRenderState;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import me.illusioner.accessor.ShelfRenderStateAccessor;

@Mixin(ShelfRenderState.class)
public class ShelfRenderStateMixin implements ShelfRenderStateAccessor {
	@Unique
    private ShelfBlockEntity shelf_numbers$blockEntity;

    public ShelfBlockEntity getBlockEntity() {
        return shelf_numbers$blockEntity;
    }

    public void setBlockEntity(ShelfBlockEntity blockEntity) {
        this.shelf_numbers$blockEntity = blockEntity;
    }
}