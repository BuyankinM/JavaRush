package com.javarush.task.task38.task3810;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Revision {
    int revision();
    Date date();
    String comment() default "";
    Author[] authors() default {};
}
