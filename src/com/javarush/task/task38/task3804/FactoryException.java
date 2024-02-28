package com.javarush.task.task38.task3804;

public class FactoryException {
    public static Throwable getException(Enum type) {
        if (type == null) return new IllegalArgumentException();

        String message = type.toString();
        message = message.replaceAll("_", " ");
        message = message.substring(0, 1) + message.substring(1).toLowerCase();

        if (type instanceof ApplicationExceptionMessage) return new Exception(message);
        else if (type instanceof DatabaseExceptionMessage) return new RuntimeException(message);
        else if (type instanceof UserExceptionMessage) return new Error(message);
        else return new IllegalArgumentException();
    }
}
