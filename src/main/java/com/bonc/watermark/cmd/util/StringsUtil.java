package com.bonc.watermark.cmd.util;

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

}
