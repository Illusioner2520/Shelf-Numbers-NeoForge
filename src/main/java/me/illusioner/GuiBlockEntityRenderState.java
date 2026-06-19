package me.illusioner;

import org.joml.Quaternionfc;
import org.joml.Vector3fc;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.world.level.block.state.BlockState;

public record GuiBlockEntityRenderState(BlockEntityRenderState renderState, BlockState blockState, Vector3fc translation, Quaternionfc rotation, Quaternionfc overrideCameraAngle, int x0, int y0, int x1, int y1, float scale, ScreenRectangle scissorArea, ScreenRectangle bounds) implements PictureInPictureRenderState {
    public GuiBlockEntityRenderState(BlockEntityRenderState renderState, BlockState blockState, Vector3fc translation, Quaternionfc rotation, Quaternionfc overrideCameraAngle, int x0, int y0, int x1, int y1, float scale, ScreenRectangle scissorArea) {
        this(renderState, blockState, translation, rotation, overrideCameraAngle, x0, y0, x1, y1, scale, scissorArea, PictureInPictureRenderState.getBounds(x0, y0, x1, y1, scissorArea));
    }
}
