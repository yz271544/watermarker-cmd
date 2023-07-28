package com.bonc.watermark.cmd.util;

import cn.hutool.core.util.ObjectUtil;
import com.bonc.watermark.cmd.consist.CmdConsists;
import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.exception.TypeConvertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class StringsUtil {

    /**
     * 重复创建水印，用sep作为分隔符
     * @param originWaterMaker 原始水印
     * @param duplicateNumber 重复个数
     * @param sep 分割符
     * @return 原始水印分割符原始水印
     */
    public static String duplicatesString(String originWaterMaker, int duplicateNumber, String sep) {
        StringBuilder sBuf = new StringBuilder();
        for (int i = 0; i < duplicateNumber; i++) {
            if (i == 0 ) {
                sBuf.append(originWaterMaker);
            } else {
                sBuf.append(sep).append(originWaterMaker);
            }
        }
        return sBuf.toString();
    }

    public static List<Map<String, String>> otherArgs(List<String> nonOptionArgs) throws CmdException {
        ObjectMapper mapper = new ObjectMapper();
        if (ObjectUtil.isNotEmpty(nonOptionArgs)) {
            String otherArgsJson = nonOptionArgs.get(0);
            try {
                return mapper.readValue(otherArgsJson, new TypeReference<List<Map<String, String>>>() {});
            } catch (JsonProcessingException e) {
                throw new TypeConvertException("otherArgs deserialization failed:" + e.getMessage());
            }
        } else {
            return null;
        }
    }

    public static boolean containSkipLoadLib(String[] args) {
        boolean isSkipLoadLib = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(CmdConsists.SKIP_LOAD_LIB)) {
                isSkipLoadLib = true;
            }
        }
        return isSkipLoadLib;
    }
}
