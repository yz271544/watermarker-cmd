package com.bonc.watermark.cmd.handle.impl;

import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.handle.DarkTypeEnum;
import com.bonc.watermark.cmd.handle.WaterMakerHandler;
import com.bonc.watermark.cmd.util.WaterMarkUtil;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class JpgWaterMakerHandler implements WaterMakerHandler {
    @Override
    public void process(String watermark, String inputFileFullPath, String outputFileFullPath,
                        DarkTypeEnum darkTypeEnum, Map<String, String> otherArgs) throws CmdException {
        if (darkTypeEnum.equals(DarkTypeEnum.EXTRACT)) {
            Mat image = Imgcodecs.imread(inputFileFullPath);
            Mat mat = WaterMarkUtil.extractWatermarkFromMat(image);
            Imgcodecs.imwrite(outputFileFullPath, mat);
        } else if (darkTypeEnum.equals(DarkTypeEnum.DARK)) {
            Mat image = Imgcodecs.imread(inputFileFullPath);
            Mat invDFT = WaterMarkUtil.addWatermarkForDark(image, watermark);
            Imgcodecs.imwrite(outputFileFullPath, invDFT);
        } else if (darkTypeEnum.equals(DarkTypeEnum.LIGHT)) {
            File file = new File(inputFileFullPath);
            BufferedImage src = null;
            try {
                src = ImageIO.read(file);
                File outputFile = new File(outputFileFullPath);
                WaterMarkUtil.addWatermarkForLight(src, watermark, otherArgs);
                ImageIO.write(src, "jpg", outputFile);
            } catch (IOException e) {
                log.error("read write image failed: " + e.getMessage());
            }
        } else {
            log.error("jps watermark only support extract dark light");
        }
    }
}
