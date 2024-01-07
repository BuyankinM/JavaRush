package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age) {
        Human personByAge = null;
        if (age <= KidGirl.MAX_AGE) personByAge = new KidGirl();
        else if (age <= TeenGirl.MAX_AGE) personByAge = new TeenGirl();
        else  personByAge = new Woman();

        return personByAge;
    }

}
