package com.bonc.watermark.cmd.handle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DarkTypeEnum {
    DARK("dark"),

    LIGHT("light"),

    SOLID("solid");

    private final String typeValue;


    DarkTypeEnum(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeValue() {
        return this.typeValue;
    }

    public static DarkTypeEnum fromTypeValueString(String typeValue) {
        for (DarkTypeEnum t : DarkTypeEnum.values()) {
            if (t.getTypeValue().equals(typeValue)) {
                return t;
            }
        }
        return null;
    }

    public static List<String> enumValues() {
        return Arrays.stream(DarkTypeEnum.values()).map(DarkTypeEnum::getTypeValue).collect(Collectors.toList());
    }

}
