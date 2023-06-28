package com.bonc.watermark.cmd.handle;

import com.bonc.watermark.cmd.handle.impl.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FileTypeEnum {
    XLSX("xlsx", new XlsxWaterMakerHandler()),
    XLS("xls", new XlsWaterMakerHandler()),
    PDF("pdf", new PdfWaterMakerHandler()),
    DOCX("docx", new DocxWaterMakerHandler()),
    DOC("doc", new DocWaterMakerHandler()),
    PNG("png", new PngWaterMakerHandler()),
    JPG("jpg", new JpgWaterMakerHandler()),
    MP4("mp4", new Mp4WaterMakerHandler());

    private final String typeValue;

    private final WaterMakerHandler waterMakerHandler;

    FileTypeEnum(String typeValue, WaterMakerHandler waterMakerHandler) {
        this.typeValue = typeValue;
        this.waterMakerHandler = waterMakerHandler;
    }

    public WaterMakerHandler get() {
        return this.waterMakerHandler;
    }

    public String getTypeValue() {
        return this.typeValue;
    }

    public static FileTypeEnum fromTypeValueString(String typeValue) {
        for (FileTypeEnum t : FileTypeEnum.values()) {
            if (t.getTypeValue().equals(typeValue)) {
                return t;
            }
        }
        return null;
    }


    public static List<String> enumValues() {
        return Arrays.stream(FileTypeEnum.values()).map(FileTypeEnum::getTypeValue).collect(Collectors.toList());
    }

}
