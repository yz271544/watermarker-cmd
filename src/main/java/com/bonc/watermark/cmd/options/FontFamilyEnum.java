package com.bonc.watermark.cmd.options;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FontFamilyEnum {
    DIALOG("Dialog", Font.DIALOG),
    DIALOG_INPUT("DialogInput", Font.DIALOG_INPUT),
    SANS_SERIF("SansSerif", Font.SANS_SERIF),
    SERIF("Serif", Font.SERIF),
    MONOSPACED("Monospaced", Font.MONOSPACED),
    SIMSUN("宋体","宋体"),
    STXINGKA("华文行楷", "华文行楷"),
    STXINWEI("华文新魏", "华文新魏");

    private final String familyName;

    private final String fontFamily;

    FontFamilyEnum(String familyName, String fontFamily) {
        this.familyName = familyName;
        this.fontFamily = fontFamily;
    }

    public String getFontFamily() {
        return this.fontFamily;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public static FontFamilyEnum fromFontFamilyName(String familyName) {
        for (FontFamilyEnum t : FontFamilyEnum.values()) {
            if (t.getFamilyName().equals(familyName)) {
                return t;
            }
        }
        return null;
    }


    public static List<String> enumValues() {
        return Arrays.stream(FontFamilyEnum.values()).map(FontFamilyEnum::getFamilyName).collect(Collectors.toList());
    }

}
