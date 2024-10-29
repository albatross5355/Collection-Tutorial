package com.example.HomeLoanApp.collectiontutorial;

import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;


public class NavigableMapTest {

    public static void main(String[] args) {
        NavigableMap<String, String> sMap = new TreeMap<>();


        sMap.put("John", "(342)113-9878");
        sMap.put("Richard", "(245)890-9045");
        sMap.put("Donna", "(205)678-9823");
        sMap.put("Ken", "(205)678-9823");

        System.out.println("Sorted Map: " + sMap);

        Map.Entry<String, String> lower = sMap.lowerEntry("Ken");
        Map.Entry<String, String> floor = sMap.lowerEntry("Ken");
        Map.Entry<String, String> higher = sMap.lowerEntry("Ken");
        Map.Entry<String, String> ceiling = sMap.lowerEntry("Ken");
        System.out.println("lower"+ lower);
        System.out.println("floor"+ floor);
        System.out.println("higher"+ higher);
        System.out.println("ceiling"+ ceiling);

        NavigableMap<String, String> sMap1 = sMap.descendingMap();
        System.out.println("reverse map"+ sMap1);



    }
}
