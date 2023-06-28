package com.bonc.watermark.cmd.util;

import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.exception.FileNotExistsOrCannotReadException;

import java.io.File;
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

}
