package com.bonc.watermark.cmd.handle;

import com.bonc.watermark.cmd.exception.CmdException;

public interface WaterMakerHandler {

    void process(String watermark, String inputFileFullPath, String outputFileFullPath, DarkTypeEnum darkTypeEnum) throws CmdException;

}
