package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];

        // collect names
        List<String> zipNames = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            zipNames.add(args[i]);
        }
        Collections.sort(zipNames);

        // collect streams
        List<FileInputStream> inputStreams = new ArrayList<>();
        for (String name : zipNames) inputStreams.add(new FileInputStream(name));

        // write result
        try (ZipInputStream is = new ZipInputStream(new SequenceInputStream(Collections.enumeration(inputStreams))))
        {
            while (true) {
                ZipEntry entry = is.getNextEntry();
                if (entry == null) break;

                try (OutputStream os = new BufferedOutputStream(new FileOutputStream(resultFileName))) {
                    final int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    for (int readBytes; (readBytes = is.read(buffer, 0, bufferSize)) > -1; ) {
                        os.write(buffer, 0, readBytes);
                    }
                    os.flush();
                }
            }
        }
    }
}
