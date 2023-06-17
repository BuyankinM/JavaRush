package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File yourFile = File.createTempFile("test", null);
        FileOutputStream fileOutputStream = new FileOutputStream(yourFile);
        FileInputStream fileInputStream = new FileInputStream(yourFile);

        Solution savedObject = new Solution(30);
        savedObject.string = "ttt";

        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(savedObject);
        fileOutputStream.close();
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        Object object = inputStream.readObject();
        Solution loadedObject = (Solution) object;
        fileInputStream.close();
        inputStream.close();

        System.out.println(savedObject.string.equals(loadedObject.string));
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
