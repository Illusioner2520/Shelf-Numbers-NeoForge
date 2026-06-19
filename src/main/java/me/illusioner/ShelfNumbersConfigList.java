package me.illusioner;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;

public class ShelfNumbersConfigList extends ContainerObjectSelectionList<ShelfNumbersConfigList.Entry> {
    private Screen screen;

    public ShelfNumbersConfigList(Minecraft minecraft, int width, ShelfNumbersConfigScreen screen) {
        super(minecraft, width, screen.layout.getContentHeight(), screen.layout.getHeaderHeight(), 25);
        this.centerListVertically = false;
        this.screen = screen;
    }

    public void addSmall(List<AbstractWidget> widgets) {
        for (int i = 0; i < widgets.size(); i += 2) {
            AbstractWidget leftWidget = widgets.get(i);
            AbstractWidget rightWidget = i >= widgets.size() - 1 ? null : widgets.get(i + 1);
            this.addEntry(ShelfNumbersConfigList.Entry.small(leftWidget, rightWidget, this.screen));
        }
    }

    @Override
    public int getRowWidth() {
        return 310;
    }

    @Override
    public int getRowLeft() {
        return this.width - this.getRowWidth();
    }

    @Override
    public int getRowRight() {
        return 10;
    }

    @Override
    public int scrollBarX() {
        return this.width - 6;
    }
    
    static class Entry extends ContainerObjectSelectionList.Entry<ShelfNumbersConfigList.Entry> {
        private List<AbstractWidget> children = new ArrayList<>();
        private Screen screen;

        private Entry(List<AbstractWidget> children, Screen screen) {
            this.children = children;
            this.screen = screen;
        }

        public static ShelfNumbersConfigList.Entry small(AbstractWidget leftWidget, AbstractWidget rightWidget, Screen screen) {
            if (rightWidget == null) {
                return new ShelfNumbersConfigList.Entry(List.of(leftWidget), screen);
            } else {
                return new ShelfNumbersConfigList.Entry(List.of(leftWidget, rightWidget), screen);
            }
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return children;
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return children;
        }

        @Override
        public void extractContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float a) {
            int x = this.screen.width - 320;
            int left = 0;

            for (AbstractWidget widget : this.children) {
                widget.setPosition(x + left, this.getContentY());
                widget.extractRenderState(graphics, mouseX, mouseY, a);
                left += 160;
            }
        }
        
    }
}
