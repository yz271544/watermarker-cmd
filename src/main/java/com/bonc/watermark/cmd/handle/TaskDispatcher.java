package com.bonc.watermark.cmd.handle;

import cn.hutool.core.util.ObjectUtil;
import com.bonc.watermark.cmd.config.FixTaskPool;
import com.bonc.watermark.cmd.consist.CmdConsists;
import com.bonc.watermark.cmd.exception.CmdArgumentInvalidException;
import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.exception.FileNotExistsOrCannotReadException;
import com.bonc.watermark.cmd.util.FileUtil;
import com.bonc.watermark.cmd.util.StringsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class TaskDispatcher {

    @Resource
    private FixTaskPool fixTaskPool;

    public List<HandlerAdapter> expose(ApplicationArguments args) throws CmdException {
        List<String> nonOptionArgs = args.getNonOptionArgs();
        Map<String, String> otherArgs = StringsUtil.otherArgs(nonOptionArgs);
        if (!args.containsOption(CmdConsists.WATERMARK) && !args.containsOption(CmdConsists.WATER_LIST_PATH)) {
            log.error("must specify the watermark.");
            throw new CmdArgumentInvalidException("must specify the watermark.");
        }
        if (args.containsOption(CmdConsists.INPUT_FILE_FULL_PATH) && args.containsOption(CmdConsists.INPUT_PATH)) {
            log.error("inputFileFullPath and inputPath can only specify one of them.");
            throw new CmdArgumentInvalidException("inputFileFullPath and inputPath can only specify one of them.");
        }
        if (args.containsOption(CmdConsists.OUTPUT_FILE_FULL_PATH) && args.containsOption(CmdConsists.OUTPUT_PATH)) {
            log.error("outputFileFullPath and outputPath can only specify one of them.");
            throw new CmdArgumentInvalidException("outputFileFullPath and outputPath can only specify one of them.");
        }

        if (args.containsOption(CmdConsists.INPUT_FILE_FULL_PATH) && args.containsOption(CmdConsists.OUTPUT_PATH)) {
            log.error("inputFileFullPath can only be combined with outputFileFullPath, and outputPath can only be combined with inputPath.");
            throw new CmdArgumentInvalidException("inputFileFullPath can only be combined with outputFileFullPath, and outputPath can only be combined with inputPath.");
        }

        if (args.containsOption(CmdConsists.OUTPUT_FILE_FULL_PATH) && args.containsOption(CmdConsists.INPUT_PATH)) {
            log.error("outputFileFullPath can only be combined with inputFileFullPath, and inputPath can only be combined with outputPath.");
            throw new CmdArgumentInvalidException("outputFileFullPath can only be combined with inputFileFullPath, and inputPath can only be combined with outputPath.");
        }

        DarkTypeEnum darkTypeEnum = DarkTypeEnum.DARK;
        if (args.containsOption(CmdConsists.DARK_TYPE)) {
            String darkTypeString = args.getOptionValues(CmdConsists.DARK_TYPE).get(0);
            darkTypeEnum = DarkTypeEnum.fromTypeValueString(darkTypeString);
        }

        if (darkTypeEnum == null) {
            throw new CmdArgumentInvalidException("not found the argument for darkType:" + args.getOptionValues(CmdConsists.DARK_TYPE).get(0));
        }

        List<HandlerAdapter> handlerAdapters = new ArrayList<>();

        String watermark = "";
        List<String> watermarkOptions = args.getOptionValues(CmdConsists.WATERMARK);
        if (ObjectUtil.isNotEmpty(watermarkOptions)) {
            watermark = watermarkOptions.get(0);
        }
        if (args.containsOption(CmdConsists.INPUT_FILE_FULL_PATH)) {
            String input = args.getOptionValues(CmdConsists.INPUT_FILE_FULL_PATH).get(0);
            if (!FileUtil.isExistsAndCanRead(input)) {
                throw new FileNotExistsOrCannotReadException(input);
            }
            HandlerAdapter handlerAdapter = new HandlerAdapter();
            handlerAdapter.setWatermark(watermark);
            handlerAdapter.setInputFileFullPath(input);
            handlerAdapter.setOutputFileFullPath(args.getOptionValues(CmdConsists.OUTPUT_FILE_FULL_PATH).get(0));
            handlerAdapter.setDarkTypeEnum(darkTypeEnum);
            handlerAdapter.setOtherArgs(otherArgs);
            handlerAdapters.add(handlerAdapter);
        } else if (args.containsOption(CmdConsists.INPUT_PATH)) {
            List<String> filesOfInputPath = new ArrayList<>();
            String fileDirSep = System.getProperties().getProperty("file.separator");
            String inputDir = args.getOptionValues(CmdConsists.INPUT_PATH).get(0);
            FileUtil.listDirFiles(inputDir, filesOfInputPath);
            if (filesOfInputPath.size() > 0) {
                for (String inputFileFullPath : filesOfInputPath) {
                    String inputFileName = FileUtil.extractFileNameFromFullPath(inputFileFullPath);
                    String outputFileFullPath = args.getOptionValues(CmdConsists.OUTPUT_PATH).get(0) + fileDirSep + inputFileName;
                    HandlerAdapter handlerAdapter = new HandlerAdapter();
                    handlerAdapter.setWatermark(watermark);
                    handlerAdapter.setInputFileFullPath(inputFileFullPath);
                    handlerAdapter.setOutputFileFullPath(outputFileFullPath);
                    handlerAdapter.setDarkTypeEnum(darkTypeEnum);
                    handlerAdapter.setOtherArgs(otherArgs);
                    handlerAdapters.add(handlerAdapter);
                }
            }
        } else if (args.containsOption(CmdConsists.WATER_LIST_PATH)) {
            // prepare water list
            List<String> waterListPathOptions = args.getOptionValues(CmdConsists.WATER_LIST_PATH);
            if (ObjectUtil.isEmpty(waterListPathOptions)) {
                throw new CmdArgumentInvalidException("not found the water list file");
            }
            String waterListPath = waterListPathOptions.get(0);
            List<String> contents = null;
            try {
                contents = FileUtil.readWholeFileToList(waterListPath);
            } catch (IOException e) {
                throw new CmdArgumentInvalidException("not found the water list file:" + waterListPath + " message:" + e.getMessage());
            }
            // prepare template path
            List<String> templatePathOptions = args.getOptionValues(CmdConsists.TEMPLATE_PATH);
            if (ObjectUtil.isEmpty(templatePathOptions)) {
                throw new CmdArgumentInvalidException("not found template file");
            }
            String templatePath = templatePathOptions.get(0);

            String fileDirSep = System.getProperties().getProperty("file.separator");
            String inputFileName = FileUtil.extractFileNameFromFullPath(templatePath);

            if (ObjectUtil.isNotEmpty(contents)) {
                for (String wantWatermark : contents) {
                    String outputFileFullPath = args.getOptionValues(CmdConsists.OUTPUT_PATH).get(0) + fileDirSep + wantWatermark + "." + inputFileName;
                    HandlerAdapter handlerAdapter = new HandlerAdapter();
                    handlerAdapter.setWatermark(wantWatermark);
                    handlerAdapter.setInputFileFullPath(templatePath);
                    handlerAdapter.setOutputFileFullPath(outputFileFullPath);
                    handlerAdapter.setDarkTypeEnum(darkTypeEnum);
                    handlerAdapter.setOtherArgs(otherArgs);
                    handlerAdapters.add(handlerAdapter);
                }
            }
        }

        return handlerAdapters;
    }


    public void dispatch(List<HandlerAdapter> handlerAdapters) {
        log.info("start submit waterMask task");
        for (HandlerAdapter handlerAdapter : handlerAdapters) {
            fixTaskPool.submit(handlerAdapter);
        }
        log.info("start detect waterMask task");
        fixTaskPool.detectTask();
        log.info("finish all of the waterMask task");
        fixTaskPool.shutdown();

    }
}