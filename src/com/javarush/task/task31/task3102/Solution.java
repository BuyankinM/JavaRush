package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> res = new ArrayList<>();

        File file = new File(root);
        List<File> files = new ArrayList<>(Arrays.asList(file.listFiles()));
        for (int i = 0; i < files.size(); i++) {
            file = files.get(i);
            if (file.isFile()) {
                res.add(file.getAbsolutePath());
            } else {
                files.addAll(Arrays.asList(file.listFiles()));
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
