package me.illusioner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.ShelfRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ShelfWidget extends AbstractWidget {
    private final BlockState shelfState;
    private final ShelfBlockEntity shelfBlockEntity;
    private final Block[] shelves = new Block[]{Blocks.ACACIA_SHELF, Blocks.BIRCH_SHELF, Blocks.BAMBOO_SHELF, Blocks.CHERRY_SHELF, Blocks.CRIMSON_SHELF, Blocks.DARK_OAK_SHELF, Blocks.JUNGLE_SHELF, Blocks.MANGROVE_SHELF, Blocks.OAK_SHELF, Blocks.PALE_OAK_SHELF, Blocks.SPRUCE_SHELF, Blocks.WARPED_SHELF};

    @SuppressWarnings("deprecation")
    public ShelfWidget(int x, int y) {
        super(x, y, 64, 64, Component.empty());
        this.shelfState = shelves[(int) (Math.random() * shelves.length)].defaultBlockState();
        this.shelfBlockEntity = new ShelfBlockEntity(BlockPos.ZERO, shelfState);
        Reference<Item> ref1 = Items.SULFUR.asItem().builtInRegistryHolder();
        Reference<Item> ref2 = Items.CINNABAR.asItem().builtInRegistryHolder();
        if (!ref1.areComponentsBound()) ref1.bindComponents(DataComponentMap.builder().set(DataComponents.MAX_STACK_SIZE, 64).set(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath("minecraft", "sulfur")).build());
        if (!ref2.areComponentsBound()) ref2.bindComponents(DataComponentMap.builder().set(DataComponents.MAX_STACK_SIZE, 64).set(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath("minecraft", "cinnabar")).build());
        ItemStack item1 = new ItemStack((Holder<Item>) ref1, 64);
        ItemStack item2 = new ItemStack((Holder<Item>) ref2, 1);
        this.shelfBlockEntity.setItem(0, item1);
        this.shelfBlockEntity.setItem(2, item2);
    }

    @Override
    protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        float centerX = getX() + getWidth() / 2.0f;
        float centerY = getY() + getHeight() / 2.0f;
        float xAngle = (float) Math.atan((centerX - mouseX) / 40.0F);
        float yAngle = (float) Math.atan((centerY - mouseY) / 40.0F);
        Quaternionf rotation = new Quaternionf().rotateZ((float) Math.toRadians(180));
        Quaternionf xRotation = new Quaternionf().rotateX(yAngle * 10.0F * (float) (Math.PI / 180.0)).rotateY(-xAngle * 10.0f * (float) (Math.PI / 180.0));
        rotation.mul(xRotation);
        BlockEntityRenderDispatcher blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
        BlockEntityRenderer<ShelfBlockEntity, ShelfRenderState> shelfRenderer = blockEntityRenderDispatcher.getRenderer(this.shelfBlockEntity);
        ShelfRenderState shelfRenderState = shelfRenderer.createRenderState();
        shelfRenderState.lightCoords = 15728880;
        shelfRenderer.extractRenderState(this.shelfBlockEntity, shelfRenderState, delta, null, null);
        Vector3f translation = new Vector3f(-0.5F, -0.5F, 0.0F);
        graphics.submitPictureInPictureRenderState(new GuiBlockEntityRenderState(shelfRenderState, this.shelfState, translation, rotation, xRotation, getX(), getY(), getX() + getWidth(), getY() + getHeight(), getWidth() * 0.8f, graphics.peekScissorStack()));
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {}
}
