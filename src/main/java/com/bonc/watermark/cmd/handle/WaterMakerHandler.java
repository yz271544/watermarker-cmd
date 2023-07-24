package com.bonc.watermark.cmd.handle;

import com.bonc.watermark.cmd.exception.CmdException;

import java.util.List;
import java.util.Map;

public interface WaterMakerHandler {

    void process(String watermark, String inputFileFullPath, String outputFileFullPath,
                 DarkTypeEnum darkTypeEnum, List<Map<String, String>> otherArgs) throws CmdException;

}
