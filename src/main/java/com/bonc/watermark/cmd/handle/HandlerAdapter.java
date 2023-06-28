package com.bonc.watermark.cmd.handle;

import com.bonc.watermark.cmd.consist.CmdConsists;
import com.bonc.watermark.cmd.exception.CmdArgumentInvalidException;
import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.exception.FileNotExistsOrCannotReadException;
import com.bonc.watermark.cmd.exception.UnImplementException;
import com.bonc.watermark.cmd.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class HandlerAdapter {

    String watermark;

    String inputFileFullPath;

    String outputFileFullPath;

    public static HandlerAdapter factory(ApplicationArguments args) throws CmdException {
        if (!args.containsOption(CmdConsists.watermark)) {
            log.error("must specify the watermark.");
            throw new CmdArgumentInvalidException("must specify the watermark.");
        }
        if (!args.containsOption(CmdConsists.inputFileFullPath)) {
            log.error("must specify the inputFileFullPath.");
            throw new CmdArgumentInvalidException("must specify the inputFileFullPath.");
        }
        if (!args.containsOption(CmdConsists.outputFileFullPath)) {
            log.error("must specify the outputFileFullPath.");
            throw new CmdArgumentInvalidException("must specify the outputFileFullPath.");
        }

        String input = args.getOptionValues(CmdConsists.inputFileFullPath).get(0);

        if (!FileUtil.isExistsAndCanRead(input)) {
            throw new FileNotExistsOrCannotReadException(input);
        }

        HandlerAdapter handlerAdapter = new HandlerAdapter();
        handlerAdapter.setWatermark(args.getOptionValues(CmdConsists.watermark).get(0));
        handlerAdapter.setInputFileFullPath(args.getOptionValues(CmdConsists.inputFileFullPath).get(0));
        handlerAdapter.setOutputFileFullPath(args.getOptionValues(CmdConsists.outputFileFullPath).get(0));
        return handlerAdapter;
    }

    public void adapter() throws CmdException {
        Optional<String> inputFileExtNameOpt = FileUtil.getExtensionByStringHandling(this.getInputFileFullPath());
        if (inputFileExtNameOpt.isPresent()) {
            String extName = inputFileExtNameOpt.get();
            FileTypeEnum fileTypeEnum = FileTypeEnum.fromTypeValueString(extName);
            if (fileTypeEnum == null) {
                throw new UnImplementException();
            }
            process(fileTypeEnum);
        } else {
            throw new UnImplementException();
        }
    }

    public void process(FileTypeEnum fileTypeEnum) throws CmdException {
        fileTypeEnum.get().process(this.getWatermark(), this.getInputFileFullPath(), this.getOutputFileFullPath());
    }

}
