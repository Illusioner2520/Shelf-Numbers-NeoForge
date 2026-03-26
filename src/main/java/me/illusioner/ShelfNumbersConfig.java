package me.illusioner;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ShelfNumbersConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<String> textColor;
    public static final ModConfigSpec.ConfigValue<String> outlineColor;
    public static final ModConfigSpec.ConfigValue<Boolean> showShadow;
    public static final ModConfigSpec.ConfigValue<Integer> fontSize;
    public static final ModConfigSpec.ConfigValue<Boolean> displayWithoutItems;
    public static final ModConfigSpec.ConfigValue<Boolean> displayWithSingleItem;
    public static final ModConfigSpec.ConfigValue<Boolean> displayWithFullStack;
    public static final ModConfigSpec.ConfigValue<Boolean> displayOnTop;
    public static final ModConfigSpec.ConfigValue<Boolean> bold;
    public static final ModConfigSpec.ConfigValue<Boolean> italics;
    public static final ModConfigSpec.ConfigValue<Boolean> underline;
    public static final ModConfigSpec.ConfigValue<Boolean> strikethrough;
    public static final ModConfigSpec.ConfigValue<Boolean> obfuscated;

    static {

        BUILDER.translation("shelf_numbers.config.title");

        textColor = BUILDER
            .comment("Color of the text (in #AARRGGBB)")
            .translation("shelf_numbers.config.text_color")
            .define("text_color", "#ffffffff");

        outlineColor = BUILDER
            .comment("Color of the text outline (in #AARRGGBB)")
            .translation("shelf_numbers.config.outline_color")
            .define("outline_color", "#00000000");

        showShadow = BUILDER
            .comment("Display a text shadow")
            .translation("shelf_numbers.config.show_shadow")
            .define("show_shadow", false);

        fontSize = BUILDER
            .comment("Font size of the text")
            .translation("shelf_numbers.config.font_size")
            .defineInRange("font_size", 30, 0, Integer.MAX_VALUE);

        displayWithoutItems = BUILDER
            .comment("Display the number even if that section of the shelf is empty")
            .translation("shelf_numbers.config.display_without_items")
            .define("display_without_items", false);

        displayWithSingleItem = BUILDER
            .comment("Display the number even if that section of the shelf only has a quantity of one")
            .translation("shelf_numbers.config.display_with_single_item")
            .define("display_with_single_item", true);

        displayWithFullStack = BUILDER
            .comment("Display the number even if that section of the shelf has a full stack of items")
            .translation("shelf_numbers.config.display_with_full_stack")
            .define("display_with_full_stack", true);

        displayOnTop = BUILDER
            .comment("Display on the top of the shelf instead")
            .translation("shelf_numbers.config.display_on_top")
            .define("display_on_top", false);

        bold = BUILDER
            .comment("Display the text as bold")
            .translation("shelf_numbers.config.bold")
            .define("bold", false);

        italics = BUILDER
            .comment("Display the text as italicized")
            .translation("shelf_numbers.config.italics")
            .define("italics", false);

        underline = BUILDER
            .comment("Display the text as underlined")
            .translation("shelf_numbers.config.underline")
            .define("underline", false);

        strikethrough = BUILDER
            .comment("Display the text as struckthrough")
            .translation("shelf_numbers.config.strikethrough")
            .define("strikethrough", false);

        obfuscated = BUILDER
            .comment("Display the text as obfuscated")
            .translation("shelf_numbers.config.obfuscated")
            .define("obfuscated", false);
    }

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static int convertColor(String hexColor) {
        try {
            return (int) Long.parseLong(hexColor.replace("#", ""), 16);
        } catch (NumberFormatException e) {
            return 0xffffffff;
        }
    }
}
