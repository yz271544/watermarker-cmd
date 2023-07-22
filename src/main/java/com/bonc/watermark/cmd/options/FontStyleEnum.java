package com.bonc.watermark.cmd.options;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FontStyleEnum {
    PLAIN("plain", Font.PLAIN),
    BOLD("bold", Font.BOLD),
    ITALIC("italic", Font.ITALIC),
    ROMAN_BASELINE("roman_baseline", Font.ROMAN_BASELINE),
    CENTER_BASELINE("center_baseline", Font.CENTER_BASELINE),
    HANGING_BASELINE("hanging_baseline", Font.HANGING_BASELINE),
    TRUETYPE_FONT("truetype_font", Font.TRUETYPE_FONT),
    TYPE1_FONT("type1_font", Font.TYPE1_FONT);

    private final String styleName;

    private final int fontStyle;

    FontStyleEnum(String styleName, int fontStyle) {
        this.styleName = styleName;
        this.fontStyle = fontStyle;
    }

    public int get() {
        return this.fontStyle;
    }

    public String getStyleName() {
        return this.styleName;
    }

    public static FontStyleEnum fromLowerStyleName(String styleName) {
        for (FontStyleEnum t : FontStyleEnum.values()) {
            if (t.getStyleName().equals(styleName)) {
                return t;
            }
        }
        return null;
    }


    public static List<String> enumValues() {
        return Arrays.stream(FontStyleEnum.values()).map(FontStyleEnum::getStyleName).collect(Collectors.toList());
    }

}
