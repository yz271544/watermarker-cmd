package com.bonc.watermark.cmd.util;

import java.io.File;
import java.util.Optional;

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

}
