package com.bonc.watermark.xlsx.resolver;

import com.bonc.watermark.cmd.options.FontFamilyEnum;
import com.bonc.watermark.cmd.options.FontStyleEnum;
import org.junit.Test;

import java.awt.*;
import java.util.List;

public class AwtFontTest {

    @Test
    public void testFontFamily() {
        Font fontFamily = new Font("宋体", Font.PLAIN, 100);
        System.out.println(fontFamily);
    }

    @Test
    public void testFontFamily2() {
        System.out.println(FontFamilyEnum.SIMSUN);

        List<String> fontFamilyEnums = FontFamilyEnum.enumValues();
        System.out.println(fontFamilyEnums);

        FontFamilyEnum dialogInput = FontFamilyEnum.fromFontFamilyName("DialogInput");
        System.out.println(dialogInput);
        System.out.println(dialogInput.getFamilyName());
        System.out.println(dialogInput.getFamilyName());


        FontFamilyEnum stxingka = FontFamilyEnum.fromFontFamilyName("华文行楷");
        System.out.println(stxingka);
        System.out.println(stxingka.getFamilyName());
        System.out.println(stxingka.getFamilyName());


    }

    @Test
    public void testFontStyleEnum() {
        List<String> strings = FontStyleEnum.enumValues();
        System.out.println(strings);

        System.out.println(FontStyleEnum.BOLD);

        FontStyleEnum italic = FontStyleEnum.fromLowerStyleName("italic");
        System.out.println(italic);
        System.out.println(italic.getStyleName());
    }

}
