package com.bookstore.tracker.helper;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;

@Slf4j
public final class FileUtil {

    private FileUtil() {

    }

    /**
     * Uses getResource to generate url of the desired file. If url successfully instantiated the mehtod creates a File
     * Object and returns the absolute path to that file. If not the method returns null.
     */
    public static File getAbsoluteStorePathFile(final String pathToFile) {
        final URL storeUrl = Thread.currentThread().getContextClassLoader().getResource(pathToFile);
        File file;
        if (null == storeUrl || storeUrl.getFile().isEmpty()) {
            file = new File(pathToFile);
        } else {
            file = new File(storeUrl.getFile());
            if (!file.exists()) {
                file = new File(pathToFile);
            } else {
                log.error("Couldn't find the file: {}", pathToFile);
            }
        }
        return file;
    }

}
