package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static Planet thePlanet;

    static {
        try {
            readKeyFromConsoleAndInitPlanet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readKeyFromConsoleAndInitPlanet() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String planetName = bufferedReader.readLine();

        if (Planet.SUN.equals(planetName)) thePlanet = Sun.getInstance();
        else if (Planet.EARTH.equals(planetName)) thePlanet = Earth.getInstance();
        else if (Planet.MOON.equals(planetName)) thePlanet = Moon.getInstance();
        else thePlanet = null;
    }
}
