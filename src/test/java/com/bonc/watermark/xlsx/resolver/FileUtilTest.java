package com.bonc.watermark.xlsx.resolver;

import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void testListDirFiles() {
        try {
            List<String> files = new ArrayList<>();
            FileUtil.listDirFiles("logs", files);
            System.out.println(files);

            for (String file : files) {
                String s = FileUtil.extractFileNameFromFullPath(file);
                System.out.println(s);
            }

        } catch (CmdException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadWholeFileToList() throws IOException {
        String inputFile = "D:\\iProject\\javapath\\watermarker-cmd\\doc\\jpg\\input\\input.txt";

        List<String> contents = FileUtil.readWholeFileToList(inputFile);

        System.out.println(contents);
    }

}
