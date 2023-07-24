package com.bonc.watermark.xlsx.resolver;

import com.bonc.watermark.cmd.util.WaterMarkUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WaterJpgTest {

    @Test
    public void testJpgMultiWater() throws IOException {
        File file = new File("D:\\iProject\\javapath\\watermarker-cmd\\doc\\jpg\\temp.jpg");
        BufferedImage src = ImageIO.read(file);

        Map<String,String> otherArgs = new HashMap<String, String>();
        otherArgs.put("fontFamily", "华文行楷");
        otherArgs.put("fontStyle", "bold");
        otherArgs.put("fontSize", "80");
        otherArgs.put("fontColor", "black");
        otherArgs.put("startX", "700");
        otherArgs.put("startY", "1030");
        WaterMarkUtil.addWatermarkForLight(src, "张三丰", otherArgs);

        Map<String,String> otherArgs2 = new HashMap<String, String>();
        otherArgs2.put("fontFamily", "宋体");
        otherArgs2.put("fontStyle", "bold");
        otherArgs2.put("fontSize", "80");
        otherArgs2.put("fontColor", "black");
        otherArgs2.put("startX", "3400");
        otherArgs2.put("startY", "295");
        WaterMarkUtil.addWatermarkForLight(src, "AZFG123481aDEf", otherArgs2);

        File outputFile = new File("D:\\iProject\\javapath\\watermarker-cmd\\doc\\jpg\\batch\\out.jpg");
        ImageIO.write(src, "jpg", outputFile);
    }


    @Test
    public void testJpgMultiWater2() throws IOException {
        File file = new File("D:\\iProject\\javapath\\watermarker-cmd\\doc\\jpg\\temp.jpg");
        BufferedImage src = ImageIO.read(file);

        Map<String,String> otherArgs2 = new HashMap<String, String>();
        otherArgs2.put("fontFamily", "宋体");
        otherArgs2.put("fontStyle", "bold");
        otherArgs2.put("fontSize", "80");
        otherArgs2.put("fontColor", "black");
        otherArgs2.put("startX", "3400");
        otherArgs2.put("startY", "295");
        WaterMarkUtil.addWatermarkForLight(src, "AZFG123481aDEf", otherArgs2);

        Map<String,String> otherArgs = new HashMap<String, String>();
        otherArgs.put("fontFamily", "华文行楷");
        otherArgs.put("fontStyle", "bold");
        otherArgs.put("fontSize", "80");
        otherArgs.put("fontColor", "black");
        otherArgs.put("startX", "700");
        otherArgs.put("startY", "1030");

        WaterMarkUtil.addWatermarkForLight(src, "张三丰", otherArgs);

        File outputFile = new File("D:\\iProject\\javapath\\watermarker-cmd\\doc\\jpg\\batch\\out.jpg");
        ImageIO.write(src, "jpg", outputFile);
    }

}
