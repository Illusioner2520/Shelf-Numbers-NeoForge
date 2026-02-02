package me.illusioner;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ShelfNumbersConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<String> textColor = BUILDER
            .comment("Color of the text (in #AARRGGBB)")
            .translation("shelf_numbers.configuration.text_color")
            .define("text_color", "#ffffffff");

    public static final ModConfigSpec.ConfigValue<String> outlineColor = BUILDER
            .comment("Color of the text outline (in #AARRGGBB)")
            .translation("shelf_numbers.configuration.outline_color")
            .define("outline_color", "#00000000");

    public static final ModConfigSpec.ConfigValue<Boolean> showShadow = BUILDER
            .comment("Display a text shadow")
            .translation("shelf_numbers.configuration.show_shadow")
            .define("show_shadow", false);

    public static final ModConfigSpec.ConfigValue<Integer> fontSize = BUILDER
            .comment("Font size of the text")
            .translation("shelf_numbers.configuration.font_size")
            .defineInRange("font_size", 30, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<Boolean> displayWithoutItems = BUILDER
            .comment("Display the number even if that section of the shelf is empty")
            .translation("shelf_numbers.configuration.display_without_items")
            .define("display_without_items", false);

    public static final ModConfigSpec.ConfigValue<Boolean> displayWithSingleItem = BUILDER
            .comment("Display the number even if that section of the shelf only has a quantity of one")
            .translation("shelf_numbers.configuration.display_with_single_item")
            .define("display_with_single_item", true);

    public static final ModConfigSpec.ConfigValue<Boolean> displayWithFullStack = BUILDER
            .comment("Display the number even if that section of the shelf has a full stack of items")
            .translation("shelf_numbers.configuration.display_with_full_stack")
            .define("display_with_full_stack", true);

    public static final ModConfigSpec.ConfigValue<Boolean> displayOnTop = BUILDER
            .comment("Display on the top of the shelf instead")
            .translation("shelf_numbers.configuration.display_on_top")
            .define("display_on_top", false);

    public static final ModConfigSpec.ConfigValue<Boolean> bold = BUILDER
            .comment("Display the text as bold")
            .translation("shelf_numbers.configuration.bold")
            .define("bold", false);

    public static final ModConfigSpec.ConfigValue<Boolean> italics = BUILDER
            .comment("Display the text as italicized")
            .translation("shelf_numbers.configuration.italics")
            .define("italics", false);

    public static final ModConfigSpec.ConfigValue<Boolean> underline = BUILDER
            .comment("Display the text as underlined")
            .translation("shelf_numbers.configuration.underline")
            .define("underline", false);

    public static final ModConfigSpec.ConfigValue<Boolean> strikethrough = BUILDER
            .comment("Display the text as struckthrough")
            .translation("shelf_numbers.configuration.strikethrough")
            .define("strikethrough", false);

    public static final ModConfigSpec.ConfigValue<Boolean> obfuscated = BUILDER
            .comment("Display the text as obfuscated")
            .translation("shelf_numbers.configuration.obfuscated")
            .define("obfuscated", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int convertColor(String hexColor) {
        return (int) Long.parseLong(hexColor.replace("#", ""), 16);
    }
}
