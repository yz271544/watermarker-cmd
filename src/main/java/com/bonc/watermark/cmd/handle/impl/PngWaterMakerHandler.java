package com.bonc.watermark.cmd.handle.impl;

import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.handle.DarkTypeEnum;
import com.bonc.watermark.cmd.handle.WaterMakerHandler;

import java.util.Map;

public class PngWaterMakerHandler implements WaterMakerHandler {
    @Override
    public void process(String watermark, String inputFileFullPath, String outputFileFullPath,
                        DarkTypeEnum darkTypeEnum, Map<String, String> otherArgs) throws CmdException {

    }
}
