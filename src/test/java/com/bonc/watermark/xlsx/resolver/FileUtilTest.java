package com.bonc.watermark.xlsx.resolver;

import com.bonc.watermark.cmd.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class FileUtilTest {

    @Test
    public void testFetchFileExtNameXlsx() {
        String inputFileFullName = "/tmp/excel/test.xlsx";
        Optional<String> extName = FileUtil.getExtensionByStringHandling(inputFileFullName);
        Assert.assertEquals("testFetchFileExtNameXlsx", "xlsx", extName.get());
    }

    @Test
    public void testFetchFileExtNameEmpty() {
        String inputFileFullName = "/tmp/excel/test";
        Optional<String> extNameOpt = FileUtil.getExtensionByStringHandling(inputFileFullName);
        Assert.assertEquals("testFetchFileExtNameEmpty", Optional.empty(), extNameOpt);
    }


}
