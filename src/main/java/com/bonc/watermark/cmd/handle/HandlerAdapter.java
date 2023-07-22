package com.bonc.watermark.cmd.handle;

import com.bonc.watermark.cmd.consist.CmdConsists;
import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.exception.UnImplementException;
import com.bonc.watermark.cmd.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class HandlerAdapter implements Callable<String> {

    String watermark;

    String inputFileFullPath;

    String outputFileFullPath;

    DarkTypeEnum darkTypeEnum;

    Map<String, String> otherArgs;

    @Override
    public String call() {
        try {
            Optional<String> inputFileExtNameOpt = FileUtil.getExtensionByStringHandling(this.getInputFileFullPath());
            if (inputFileExtNameOpt.isPresent()) {
                String extName = inputFileExtNameOpt.get();
                FileTypeEnum fileTypeEnum = FileTypeEnum.fromTypeValueString(extName);
                if (fileTypeEnum == null) {
                    throw new UnImplementException();
                }
                process(fileTypeEnum);
                return CmdConsists.SUCCESS;
            } else {
                throw new UnImplementException();
            }
        } catch (CmdException e) {
            log.error("adapter run exception: {}, inputFileFullPath: {}", e.getMessage(), inputFileFullPath);
            return CmdConsists.FAILED;
        }
    }

    public void process(FileTypeEnum fileTypeEnum) throws CmdException {
        log.info("start add water mask for {}", this.inputFileFullPath);
        fileTypeEnum.get().process(this.getWatermark(), this.getInputFileFullPath(), this.getOutputFileFullPath(),
                this.getDarkTypeEnum(), this.getOtherArgs());
        log.info("finish added the water mask for {}", this.inputFileFullPath);
    }

}
