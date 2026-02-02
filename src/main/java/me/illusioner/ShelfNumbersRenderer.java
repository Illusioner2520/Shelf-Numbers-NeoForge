package me.illusioner;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ShelfRenderer;
import net.minecraft.client.renderer.blockentity.state.ShelfRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;
import net.minecraft.world.phys.Vec3;
import javax.annotation.Nullable;

public class ShelfNumbersRenderer extends ShelfRenderer {
    private final Font font;

    public ShelfNumbersRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.font = context.font();
    }

    @Override
    public void extractRenderState(ShelfBlockEntity shelfBlockEntity, ShelfRenderState shelfRenderState, float f, Vec3 vec3, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        super.extractRenderState(shelfBlockEntity, shelfRenderState, f, vec3, crumblingOverlay);
        ((ShelfRenderStateAccessor) shelfRenderState).setBlockEntity(shelfBlockEntity);
    }

    @Override
    public void submit(ShelfRenderState shelfRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(shelfRenderState, poseStack, submitNodeCollector, cameraRenderState);
        poseStack.pushPose();
        Direction direction = (Direction) shelfRenderState.blockState.getValue(ShelfBlock.FACING);
        float shelfDir = -direction.toYRot();
        NonNullList<ItemStack> items = ((ShelfRenderStateAccessor) shelfRenderState).getBlockEntity().getItems();
        for (int i = 0; i < shelfRenderState.items.length; i++) {
            this.submitItemNumber(shelfRenderState, poseStack, submitNodeCollector, cameraRenderState, i, shelfDir, items.get(i).getCount(), items.get(i).getMaxStackSize());
        }
        poseStack.popPose();
    }

    public void submitItemNumber(ShelfRenderState shelfRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState, int index, float shelfDir, int count, int maxStackSize) {
        poseStack.pushPose();
        float x = (float) (index - 1) * 0.3125F;
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(shelfDir));
        poseStack.translate(x, ShelfNumbersConfig.displayOnTop.get() ? 0.375f : -0.375F, -0.187F);
        float scale = ShelfNumbersConfig.fontSize.get() / 1620.0f;
        poseStack.scale(scale, -scale, scale);
        String text = Integer.toString(count);
        if (count == 0 && !ShelfNumbersConfig.displayWithoutItems.get()) text = "";
        if (count == 1 && !ShelfNumbersConfig.displayWithSingleItem.get()) text = "";
        if (count == maxStackSize && !ShelfNumbersConfig.displayWithFullStack.get()) text = "";
        Style style = Style.EMPTY.withBold(ShelfNumbersConfig.bold.get()).withItalic(ShelfNumbersConfig.italics.get()).withUnderlined(ShelfNumbersConfig.underline.get()).withStrikethrough(ShelfNumbersConfig.strikethrough.get()).withObfuscated(ShelfNumbersConfig.obfuscated.get());
        FormattedCharSequence formattedCharSequence = FormattedCharSequence.forward(text, style);
        float width = (float) (-this.font.width(text) / 2);
        float height = (float) (-this.font.lineHeight / 2);
        submitNodeCollector.submitText(poseStack, width, height, formattedCharSequence, ShelfNumbersConfig.showShadow.get(), DisplayMode.POLYGON_OFFSET, shelfRenderState.lightCoords, ShelfNumbersConfig.convertColor(ShelfNumbersConfig.textColor.get()), 0, ShelfNumbersConfig.convertColor(ShelfNumbersConfig.outlineColor.get()));
        poseStack.popPose();
    }
}