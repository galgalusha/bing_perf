package com.kenshoo.mini.ks.helpers;

import com.microsoft.bingads.internal.utilities.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
    public static int copy(InputStream input, OutputStream output) throws IOException {
        if (input == null) {
            return 0;
        } else {
            FileUtils.copy(input, output);
            return 0;
        }
    }

}
