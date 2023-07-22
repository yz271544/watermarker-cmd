package com.bonc.watermark.cmd.handle;

import com.bonc.watermark.cmd.exception.CmdException;

import java.util.Map;

public interface WaterMakerHandler {

    void process(String watermark, String inputFileFullPath, String outputFileFullPath,
                 DarkTypeEnum darkTypeEnum, Map<String, String> otherArgs) throws CmdException;

}
