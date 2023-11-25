package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/

public class Solution {
    public static int countDirs;
    public static int countFiles;
    public static long allSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Path dirPath = Paths.get(br.readLine());

        if (!Files.isDirectory(dirPath)) {
            System.out.println(dirPath.toString() + " - не папка");
            return;
        }
        Files.walkFileTree(dirPath, new FileVisitor());
        System.out.println("Всего папок - " + (countDirs - 1));
        System.out.println("Всего файлов - " + countFiles);
        System.out.println("Общий размер - " + allSize);
    }

    public static class FileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            countDirs++;
            return super.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            countFiles++;
            allSize += attrs.size();
            return super.visitFile(file, attrs);
        }
    }
}
