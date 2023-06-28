package com.bonc.watermark.cmd.handle.impl;

import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.handle.WaterMakerHandler;
import com.bonc.watermark.cmd.util.StringsUtil;
import com.spire.xls.ExcelVersion;
import com.spire.xls.ViewMode;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class XlsxWaterMakerHandler implements WaterMakerHandler {

    @Override
    public void process(String watermark, String inputFileFullPath, String outputFileFullPath) throws CmdException {
        //加载Excel测试文档
        Workbook wb = new Workbook();
        wb.loadFromFile(inputFileFullPath);

        // 设置水印一行显示个数
        String watermarkTo = StringsUtil.duplicatesString(watermark, 6, "     ");

        //设置文本和字体大小
        Font font = new Font("宋体", Font.PLAIN, 25);

        for (int i = 0; i < wb.getWorksheets().getCount(); i++) {
            Worksheet sheet = wb.getWorksheets().get(i);
            //调用DrawText() 方法插入图片
            BufferedImage imgWtrmrk = drawText(watermarkTo, font, Color.gray, Color.white, sheet.getPageSetup().getPageHeight(), sheet.getPageSetup().getPageWidth());

            //将图片设置为页眉
            sheet.getPageSetup().setCenterHeaderImage(imgWtrmrk);
            sheet.getPageSetup().setCenterHeader("&G");

            //将显示模式设置为Layout
            sheet.setViewMode(ViewMode.Normal);
        }

        //保存文档
        wb.saveToFile(outputFileFullPath, ExcelVersion.Version2013);

    }

    private BufferedImage drawText(String text, Font font, Color textColor, Color backColor, double height, double width) {

        //定义图片宽度和高度
        BufferedImage img = new BufferedImage((int) width, (int) height, TYPE_INT_ARGB);

        Graphics2D loGraphic = img.createGraphics();

        //获取文本size
        FontMetrics loFontMetrics = loGraphic.getFontMetrics(font);
        int liStrWidth = loFontMetrics.stringWidth(text);
        int liStrHeight = loFontMetrics.getHeight();

        //文本显示样式及位置
        loGraphic.setColor(backColor);
        loGraphic.fillRect(0, 0, (int) width, (int) height);
        loGraphic.translate(((int) width - liStrWidth) / 2, ((int) height - liStrHeight) / 2);
        loGraphic.rotate(Math.toRadians(-45));

        loGraphic.translate(-((int) width - liStrWidth) / 2, -((int) height - liStrHeight) / 2);
        loGraphic.setFont(font);
        loGraphic.setColor(textColor);

        loGraphic.drawString(text, ((int) width - liStrWidth) / 6, ((int) height - liStrHeight) / 6);
        loGraphic.drawString(text, ((int) width - liStrWidth) / 3, ((int) height - liStrHeight) / 3);
        loGraphic.drawString(text, ((int) width - liStrWidth) / 2, ((int) height - liStrHeight) / 2);
        loGraphic.drawString(text, ((int) width - liStrWidth) / 1.5f, ((int) height - liStrHeight) / 1.5f);
        loGraphic.drawString(text, ((int) width - liStrWidth) / 1.2f, ((int) height - liStrHeight) / 1.2f);
        loGraphic.drawString(text, ((int) width - liStrWidth), ((int) height - liStrHeight));
        loGraphic.drawString(text, ((int) width - liStrWidth) / 0.8f, ((int) height - liStrHeight) / 0.8f);
        loGraphic.drawString(text, ((int) width - liStrWidth) / 0.7f, ((int) height - liStrHeight) / 0.7f);

        loGraphic.dispose();
        return img;
    }


}
