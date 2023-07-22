package com.bonc.watermark.cmd.util;

import com.bonc.watermark.cmd.exception.CmdArgumentInvalidException;
import com.bonc.watermark.cmd.exception.CmdException;

import java.awt.*;
import java.lang.reflect.Field;

public class WaterColor {

    public static Color getColor(String colorName) throws CmdException {
        Color color;
        try {
            Field field = Color.class.getField(colorName);
            color = (Color)field.get(null);
        } catch (Exception e) {
            throw new CmdArgumentInvalidException("颜色名称不正确:" + e.getMessage());
        }
        return color;
    }

}
