package com.bonc.watermark.xlsx.resolver;

import com.bonc.watermark.cmd.util.StringsUtil;
import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testDuplicateWatermark() {
        String watermarkDuplicate =
                StringsUtil.duplicatesString("内部专用", 5, "     ");
        Assert.assertEquals("重复水印测试testDuplicateWatermark", "内部专用     内部专用     内部专用     内部专用     内部专用",
                watermarkDuplicate);
    }

    @Test
    public void testArgs() {
        String argPair = "-Dsss";
        String pairKey = argPair.replaceAll("^-D", "");
        System.out.println(pairKey);
    }

}
