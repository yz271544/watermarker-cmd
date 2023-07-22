package com.bonc.watermark.xlsx.resolver;

import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.util.WaterColor;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Field;

public class AwtColorTest {

    @Test
    public void testGetRED() {
        System.out.println(Color.RED);
        Color color;
        try {
            Field field = Color.class.getField("red");
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }
        System.out.println(color);
    }

    @Test
    public void testWaterColor1() throws CmdException {
        System.out.println(Color.green);
        Color green = WaterColor.getColor("green");
        System.out.println(green);
        Assert.assertEquals(Color.green, green);
    }
}
