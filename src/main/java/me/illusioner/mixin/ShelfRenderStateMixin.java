package me.illusioner.mixin;

import net.minecraft.client.renderer.blockentity.state.ShelfRenderState;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import me.illusioner.ShelfRenderStateAccessor;

@Mixin(ShelfRenderState.class)
public class ShelfRenderStateMixin implements ShelfRenderStateAccessor {
	@Unique
    private ShelfBlockEntity illusioner$blockEntity;

    // Getter for our renderer
    public ShelfBlockEntity getBlockEntity() {
        return illusioner$blockEntity;
    }

    // Setter, can be called when creating the render state
    public void setBlockEntity(ShelfBlockEntity blockEntity) {
        this.illusioner$blockEntity = blockEntity;
    }
}