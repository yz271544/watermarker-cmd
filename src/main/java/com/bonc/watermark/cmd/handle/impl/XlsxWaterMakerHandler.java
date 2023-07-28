package com.bonc.watermark.cmd.handle.impl;

import cn.hutool.core.util.ObjectUtil;
import com.bonc.watermark.cmd.config.WorkbookProperties;
import com.bonc.watermark.cmd.consist.CmdConsists;
import com.bonc.watermark.cmd.exception.CmdArgumentInvalidException;
import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.handle.DarkTypeEnum;
import com.bonc.watermark.cmd.handle.WaterMakerHandler;
import com.bonc.watermark.cmd.options.FontAndLocate;
import com.bonc.watermark.cmd.util.SpringContextHolder;
import com.bonc.watermark.cmd.util.StringsUtil;
import com.spire.xls.*;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
@Slf4j
public class XlsxWaterMakerHandler implements WaterMakerHandler {

    @Override
    public void process(String watermark, String inputFileFullPath, String outputFileFullPath,
                        DarkTypeEnum darkTypeEnum, List<Map<String, String>> otherArgs) throws CmdException {
        //加载Excel测试文档
        Workbook wb = new Workbook();
        wb.loadFromFile(inputFileFullPath);

        // 设置水印一行显示个数
        String watermarkTo = StringsUtil.duplicatesString(watermark, 6, "     ");

        //设置文本和字体大小
        Font font = null;
        FontAndLocate fontAndLocate = null;
        if (ObjectUtil.isNotEmpty(otherArgs)) {
            Map<String, String> firstOtherArgs = otherArgs.get(0);
            fontAndLocate = new FontAndLocate(firstOtherArgs);
            font = new Font(fontAndLocate.getFontFamilyName(), fontAndLocate.getFontStyle(), fontAndLocate.getFontSize());
        } else {
            font = new Font("宋体", Font.PLAIN, 25);
        }

        for (int i = 0; i < wb.getWorksheets().getCount(); i++) {
            Worksheet sheet = wb.getWorksheets().get(i);
            sheet.getAllocatedRange().autoFitRows();
            sheet.getAllocatedRange().autoFitColumns();
            sheet.getAutoFilters().setRange(sheet.getRange());
            //调用DrawText() 方法插入图片
            BufferedImage imgWtrmrk = null;
            if (ObjectUtil.isNotEmpty(fontAndLocate)) {
                imgWtrmrk = drawText(watermarkTo, font, fontAndLocate.getFontColor(), Color.white, sheet.getPageSetup().getPageHeight(), sheet.getPageSetup().getPageWidth());
            } else {
                imgWtrmrk = drawText(watermarkTo, font, Color.gray, Color.white, sheet.getPageSetup().getPageHeight(), sheet.getPageSetup().getPageWidth());
            }

            //将图片设置为页眉
            sheet.getPageSetup().setCenterHeaderImage(imgWtrmrk);
            sheet.getPageSetup().setCenterHeader("&G");

            //将显示模式设置为Layout
            switch (darkTypeEnum) {
                case DARK:
                    sheet.setViewMode(ViewMode.Normal);
                    break;
                case LIGHT:
                    sheet.setViewMode(ViewMode.Layout);
                    break;
                case SOLID:
                    drawSolidWaterMarker(sheet, imgWtrmrk);
                    break;
                default:
                    throw new CmdArgumentInvalidException("not found the argument for darkType");
            }
            WorkbookProperties workbookProperties = (WorkbookProperties) SpringContextHolder.getBean("workbookProperties");
            sheet.protect(workbookProperties.getPassword(), EnumSet.of(SheetProtectionType.Filtering));
        }
        //保存文档
        wb.saveToFile(outputFileFullPath, ExcelVersion.Version2013);

    }

    private void drawSolidWaterMarker(Worksheet sheet, BufferedImage imgWtrmrk) {
        int maxColumn = sheet.getLastColumn();
        int maxRow = sheet.getLastRow();

        int numColumn = (int) Math.ceil(maxColumn / CmdConsists.IMAGE_WIDTH);
        int numRow = (int) Math.ceil(maxRow / CmdConsists.IMAGE_LENGTH);
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                int pinRow = (int) (i * CmdConsists.IMAGE_LENGTH + 2); // 添加过滤筛选按钮，露出第一行
                int pinCol = (int) (j * CmdConsists.IMAGE_WIDTH + 1);
                sheet.getPictures().add(pinRow, pinCol, imgWtrmrk);
            }
        }
//        WorkbookProperties workbookProperties = (WorkbookProperties) SpringContextHolder.getBean("workbookProperties");
//        sheet.protect(workbookProperties.getPassword(), EnumSet.of(SheetProtectionType.Filtering));
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
        img = loGraphic.getDeviceConfiguration().createCompatibleImage( (int) width, (int) height, Transparency.TRANSLUCENT);
        loGraphic = img.createGraphics();

        loGraphic.setFont(font);
        loGraphic.setColor(textColor);
        loGraphic.rotate(Math.toRadians(-45));
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
