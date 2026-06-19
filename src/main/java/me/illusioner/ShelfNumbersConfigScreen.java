package me.illusioner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ShelfNumbersConfigScreen extends Screen {
    public HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    private Screen lastScreen;
    private ShelfNumbersConfigList list;
    private ShelfWidget shelfWidget;

    public ShelfNumbersConfigScreen(Screen parent) {
        super(Component.translatable("shelf_numbers.config"));
        this.lastScreen = parent;
    }

    @Override
    public void onClose() {
        minecraft.setScreenAndShow(this.lastScreen);
        GsonBuilder gsonBuilder  = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        try (FileWriter writer = new FileWriter("config/shelf_numbers.json")) {
            gson.toJson(new ShelfNumbersConfig(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        this.layout.addTitleHeader(this.title, this.font);

        this.list = this.layout.addToContents(new ShelfNumbersConfigList(minecraft, width, this));
        this.shelfWidget = this.layout.addToContents(new ShelfWidget(0,0), b -> {
            b.alignHorizontallyLeft();
        });

        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose()).width(200).build());

        List<AbstractWidget> options = new ArrayList<>();

        EditBox textColorEdit = new EditBox(font, 150, 20, Component.translatable("shelf_numbers.config.text_color"));
        textColorEdit.setHint(Component.literal("#ffffffff"));
        Tooltip textColorTooltip = Tooltip.create(Component.translatable("shelf_numbers.config.text_color"));
        textColorEdit.setResponder(value -> {
            colorResponder(value, textColorEdit, textColorTooltip);
            ShelfNumbersConfig.textColor = convertColor(value);
        });
        textColorEdit.setValue(getColorValue(ShelfNumbersConfig.textColor));
        options.add(textColorEdit);

        EditBox outlineColorEdit = new EditBox(font, 150, 20, Component.translatable("shelf_numbers.config.outline_color"));
        outlineColorEdit.setHint(Component.literal("#ffffffff"));
        Tooltip outlineColorTooltip = Tooltip.create(Component.translatable("shelf_numbers.config.outline_color"));
        outlineColorEdit.setResponder(value -> {
            colorResponder(value, outlineColorEdit, outlineColorTooltip);
            ShelfNumbersConfig.outlineColor = convertColor(value);
        });
        outlineColorEdit.setValue(getColorValue(ShelfNumbersConfig.outlineColor));
        options.add(outlineColorEdit);

        CycleButton<Boolean> showShadow = CycleButton.onOffBuilder(ShelfNumbersConfig.showShadow).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.show_shadow"), (button, value) -> {
            ShelfNumbersConfig.showShadow = value;
        });
        options.add(showShadow);

        ShelfNumbersConfigScreen.FontSizeSlider fontSize = new FontSizeSlider(0, 0, 150, 20, Component.translatable("shelf_numbers.config.font_size"), ShelfNumbersConfig.fontSize);
        fontSize.updateMessage();
        options.add(fontSize);

        CycleButton<Boolean> displayWithoutItems = CycleButton.onOffBuilder(ShelfNumbersConfig.displayWithoutItems).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.display_without_items"), (button, value) -> {
            ShelfNumbersConfig.displayWithoutItems = value;
        });
        options.add(displayWithoutItems);

        CycleButton<Boolean> displayWithSingleItem = CycleButton.onOffBuilder(ShelfNumbersConfig.displayWithSingleItem).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.display_with_single_item"), (button, value) -> {
            ShelfNumbersConfig.displayWithSingleItem = value;
        });
        options.add(displayWithSingleItem);

        CycleButton<Boolean> displayWithFullStack = CycleButton.onOffBuilder(ShelfNumbersConfig.displayWithFullStack).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.display_with_full_stack"), (button, value) -> {
            ShelfNumbersConfig.displayWithFullStack = value;
        });
        options.add(displayWithFullStack);

        CycleButton<Boolean> displayOnTop = CycleButton.onOffBuilder(ShelfNumbersConfig.displayOnTop).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.display_on_top"), (button, value) -> {
            ShelfNumbersConfig.displayOnTop = value;
        });
        options.add(displayOnTop);

        CycleButton<Boolean> bold = CycleButton.onOffBuilder(ShelfNumbersConfig.bold).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.bold"), (button, value) -> {
            ShelfNumbersConfig.bold = value;
        });
        options.add(bold);

        CycleButton<Boolean> italics = CycleButton.onOffBuilder(ShelfNumbersConfig.italics).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.italics"), (button, value) -> {
            ShelfNumbersConfig.italics = value;
        });
        options.add(italics);

        CycleButton<Boolean> underline = CycleButton.onOffBuilder(ShelfNumbersConfig.underline).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.underline"), (button, value) -> {
            ShelfNumbersConfig.underline = value;
        });
        options.add(underline);

        CycleButton<Boolean> strikethrough = CycleButton.onOffBuilder(ShelfNumbersConfig.strikethrough).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.strikethrough"), (button, value) -> {
            ShelfNumbersConfig.strikethrough = value;
        });
        options.add(strikethrough);

        CycleButton<Boolean> obfuscated = CycleButton.onOffBuilder(ShelfNumbersConfig.obfuscated).create(0, 0, 150, 20, Component.translatable("shelf_numbers.config.obfuscated"), (button, value) -> {
            ShelfNumbersConfig.obfuscated = value;
        });
        options.add(obfuscated);

        this.list.addSmall(options);

        layout.visitWidgets(button -> {
            this.addRenderableWidget(button);
        });

        this.repositionElements();
    }

    @Override
    public void repositionElements() {
        this.layout.arrangeElements();
        if (this.list != null) {
            this.list.updateSize(width, layout);
        }
        int size = Math.min(this.layout.getContentHeight(), this.layout.getWidth() - this.list.getRowWidth() - 14);
        if (size <= 0) size = 1;
        this.shelfWidget.setSize(size, this.layout.getContentHeight());
        this.shelfWidget.setY(this.layout.getHeaderHeight());
        int x = (this.layout.getWidth() - this.list.getRowWidth() - 14 - size) / 2;
        if (x < 0) x = 0;
        this.shelfWidget.setX(x);
    }

    public void colorResponder(String value, EditBox editBox, Tooltip tooltip) {
        try {
            Long.parseLong(value.replace("#", ""), 16);
            editBox.setTextColor(0xFFE0E0E0);
            editBox.setTooltip(tooltip);
        } catch (NumberFormatException err) {
            editBox.setTextColor(0xFFDF5050);
            editBox.setTooltip(Tooltip.create(Component.translatable("shelf_numbers.config.invalid_color")));
        }
    }

    public String getColorValue(int number) {
        String str = Integer.toHexString(number);
        while (str.length() < 8) str = "0" + str;
        return "#" + str;
    }

    public static int convertColor(String hexColor) {
        try {
            return (int) Long.parseLong(hexColor.replace("#", ""), 16);
        } catch (NumberFormatException err) {
            return 0xffffffff;
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
    }

    public static class FontSizeSlider extends AbstractSliderButton {
        public FontSizeSlider(int x, int y, int width, int height, Component message, int initialValue) {
            super(x, y, width, height, message, initialValue / 100.0);
        }

        @Override
        protected void applyValue() {
            ShelfNumbersConfig.fontSize = (int) (100 * this.value);
        }

        @Override
        protected void updateMessage() {
            this.setMessage(Component.translatable("shelf_numbers.config.font_size", (int) (100 * this.value)));
        } 
    }
}
