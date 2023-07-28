package com.bonc.watermark.cmd.options;

import cn.hutool.core.util.ObjectUtil;
import com.bonc.watermark.cmd.consist.CmdConsists;
import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.util.WaterColor;

import java.awt.*;
import java.util.Map;

public class FontAndLocate {

    String fontFamilyName;
    int fontStyle;
    int fontSize;
    Color fontColor;
    int locateX;
    int locateY;

    public FontAndLocate(Map<String, String> otherArgs) {
        fontFamilyName = otherArgs.getOrDefault(CmdConsists.WATERMARK_FONT_FAMILY, FontFamilyEnum.SIMSUN.getFontFamily());
        String inputFontStyle = otherArgs.get(CmdConsists.WATERMARK_FONT_STYLE);
        if (ObjectUtil.isNotEmpty(inputFontStyle)) {
            FontStyleEnum fontStyleEnum = FontStyleEnum.fromLowerStyleName(inputFontStyle);
            if (fontStyleEnum != null) {
                fontStyle = fontStyleEnum.get();
            } else {
                fontStyle = FontStyleEnum.PLAIN.get();
            }
        } else {
            fontStyle = FontStyleEnum.PLAIN.get();
        }

        String inputFontSize = otherArgs.get(CmdConsists.WATERMARK_FONT_SIZE);
        if (ObjectUtil.isNotEmpty(inputFontSize)) {
            fontSize = Integer.parseInt(inputFontSize);
        } else {
            fontSize = 80;
        }

        String inputFontColor = otherArgs.get(CmdConsists.WATERMARK_FONT_COLOR);
        if (ObjectUtil.isNotEmpty(inputFontColor)) {
            try {
                fontColor = WaterColor.getColor(inputFontColor);
            } catch (CmdException e) {
                fontColor = new Color(0, 0, 0, 255);
            }
        } else if (ObjectUtil.isNotEmpty(otherArgs.get(CmdConsists.WATERMARK_FONT_COLOR_R)) &&
                ObjectUtil.isNotEmpty(otherArgs.get(CmdConsists.WATERMARK_FONT_COLOR_G)) &&
                ObjectUtil.isNotEmpty(otherArgs.get(CmdConsists.WATERMARK_FONT_COLOR_B))
        ) {
            try {
                String colorR = otherArgs.get(CmdConsists.WATERMARK_FONT_COLOR_R);
                String colorG = otherArgs.get(CmdConsists.WATERMARK_FONT_COLOR_G);
                String colorB = otherArgs.get(CmdConsists.WATERMARK_FONT_COLOR_B);
                Integer cR = Integer.valueOf(colorR);
                Integer cG = Integer.valueOf(colorG);
                Integer cB = Integer.valueOf(colorB);
                fontColor = new Color(cR, cG, cB, 255);
            } catch (NumberFormatException e) {
                fontColor = new Color(0, 0, 0, 255);
            }
        } else {
            fontColor = new Color(0, 0, 0, 255);
        }

        String inputStartX = otherArgs.get(CmdConsists.WATERMARK_START_X);
        if (ObjectUtil.isNotEmpty(inputStartX)) {
            locateX = Integer.parseInt(inputStartX);
        } else {
            locateX = 15;
        }

        String inputStartY = otherArgs.get(CmdConsists.WATERMARK_START_Y);
        if (ObjectUtil.isNotEmpty(inputStartY)) {
            locateY = Integer.parseInt(inputStartY);
        } else {
            locateY = 15;
        }
    }

    public String getFontFamilyName() {
        return fontFamilyName;
    }

    public void setFontFamilyName(String fontFamilyName) {
        this.fontFamilyName = fontFamilyName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public int getLocateX() {
        return locateX;
    }

    public void setLocateX(int locateX) {
        this.locateX = locateX;
    }

    public int getLocateY() {
        return locateY;
    }

    public void setLocateY(int locateY) {
        this.locateY = locateY;
    }

}
