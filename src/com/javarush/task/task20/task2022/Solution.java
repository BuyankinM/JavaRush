package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;

    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {

        Solution savedObject = new Solution("d:\\1.txt");
        savedObject.writeObject("test 1");
        savedObject.close();

        FileOutputStream fo = new FileOutputStream("d:\\exp.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fo);
        outputStream.writeObject(savedObject);
        fo.close();
        outputStream.close();

        FileInputStream fi = new FileInputStream("d:\\exp.txt");
        ObjectInputStream inputStream = new ObjectInputStream(fi);
        Solution loadedObject = (Solution) inputStream.readObject();
        fi.close();
        inputStream.close();

        loadedObject.writeObject("test2");
        loadedObject.close();
    }
}
