package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.Helper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile(null, null);
            Files.deleteIfExists(this.path);
            Files.createFile(this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        long size = 0;
        try {
            size = Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public void putEntry(Entry entry) {
        try (OutputStream outStream = Files.newOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(outStream)) {
            out.writeObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0) {
            return null;
        }

        try (InputStream inputStream = Files.newInputStream(path);
             ObjectInputStream in = new ObjectInputStream(inputStream)) {
            return (Entry) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
