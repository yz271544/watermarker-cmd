package com.bonc.watermark.cmd.util;

import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.exception.FileNotExistsOrCannotReadException;

import java.io.*;
import java.util.*;

public class FileUtil {

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static boolean isExistsAndCanRead(String inputFileFullPath) {
        File file = new File(inputFileFullPath);
        return file.exists() && file.canRead();
    }

    public static void listDirFiles(String dir, List<String> fileList) throws CmdException {
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            throw new FileNotExistsOrCannotReadException();
        }
        if (dirFile.isDirectory()) {
            for (File file : Objects.requireNonNull(dirFile.listFiles())) {
                if (file.isDirectory()) {
                    listDirFiles(file.getAbsolutePath(), fileList);
                } else {
                    fileList.add(file.getAbsolutePath());
                }
            }
        }
    }

    public static String extractFileNameFromFullPath(String fileFullPath) {
        File file = new File(fileFullPath);
        return file.getName();
    }

    public static List<String> readWholeFileToList(String fileFullPath) throws IOException {
        List<String> contents = new ArrayList<>();
        FileInputStream fin = new FileInputStream(fileFullPath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        while((strTmp = buffReader.readLine())!=null){
            contents.add(strTmp);
        }
        buffReader.close();
        reader.close();
        fin.close();
        return contents;
    }

}
