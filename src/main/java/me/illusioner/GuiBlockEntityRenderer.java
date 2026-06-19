package me.illusioner;

import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;

import com.mojang.blaze3d.platform.Lighting.Entry;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;

public class GuiBlockEntityRenderer extends PictureInPictureRenderer<GuiBlockEntityRenderState> {
    private BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private BlockModelResolver blockModelResolver;

    public GuiBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, ModelManager modelManager) {
        this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
        this.blockModelResolver = new BlockModelResolver(modelManager);
    }

    @Override
    public Class<GuiBlockEntityRenderState> getRenderStateClass() {
        return GuiBlockEntityRenderState.class;
    }

    @Override
    protected String getTextureLabel() {
        return "block_entity";
    }

    @Override
    protected float getTranslateY(int height, int guiScale) {
        return height / 2.0f;
    }

    @Override
    protected void renderToTexture(GuiBlockEntityRenderState renderState, PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector) {

        Minecraft.getInstance().gameRenderer.lighting().setupFor(Entry.ENTITY_IN_UI);
        Vector3fc translation = renderState.translation();
        poseStack.translate(translation.x(), translation.y(), translation.z());
        poseStack.rotateAround(renderState.rotation(), 0.5f, 0.5f, 0.5f);
        Quaternionfc overriddenCameraAngle = renderState.overrideCameraAngle();
        CameraRenderState cameraRenderState = new CameraRenderState();
        if (overriddenCameraAngle != null) {
            cameraRenderState.orientation = overriddenCameraAngle.conjugate(new Quaternionf()).rotateY((float) Math.PI);
        }
        poseStack.pushPose();
        BlockModelRenderState blockModelRenderState = new BlockModelRenderState();
        blockModelResolver.update(blockModelRenderState, renderState.blockState(), null);
        blockModelRenderState.submit(poseStack, submitNodeCollector, renderState.renderState().lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
        poseStack.pushPose();
        this.blockEntityRenderDispatcher.submit(renderState.renderState(), poseStack, submitNodeCollector, cameraRenderState);
        poseStack.popPose();
    }

}
