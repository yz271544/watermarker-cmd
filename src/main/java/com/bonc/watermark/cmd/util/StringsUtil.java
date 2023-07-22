package com.bonc.watermark.cmd.util;

import cn.hutool.core.util.ObjectUtil;
import com.bonc.watermark.cmd.consist.CmdConsists;
import com.bonc.watermark.cmd.exception.CmdArgumentInvalidException;
import com.bonc.watermark.cmd.exception.CmdException;

import java.util.HashMap;
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

    public static Map<String, String> otherArgs(List<String> nonOptionArgs) throws CmdException {
        Map<String, String> otherArgs = new HashMap<>();
        for (String nonOptionArg : nonOptionArgs) {
            String[] argsPair = nonOptionArg.split(CmdConsists.ARGS_SPLIT);
            String argsName = null;
            if (ObjectUtil.isNotEmpty(argsPair[0])) {
                String argsNameWithPrefix = argsPair[0];
                argsName = argsNameWithPrefix.replaceAll("^-D", "");
                /*if (argsNameWithPrefix.lastIndexOf(CmdConsists.OTHER_ARGS_PREFIX) > 0) {
                    argsName = argsNameWithPrefix.substring(argsNameWithPrefix.indexOf(CmdConsists.OTHER_ARGS_PREFIX));
                }*/
            } else {
                throw new CmdArgumentInvalidException("nonOptionArgs must startwith -DargName=argValue");
            }
            String argsValue = null;
            if (ObjectUtil.isNotEmpty(argsPair[1])) {
                argsValue = argsPair[1];
            } else {
                throw new CmdArgumentInvalidException("nonOptionArgs must startwith -DargName=argValue");
            }
            otherArgs.put(argsName, argsValue);
        }
        return otherArgs;
    }
}
