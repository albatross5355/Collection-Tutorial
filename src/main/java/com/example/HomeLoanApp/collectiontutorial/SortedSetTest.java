package com.example.HomeLoanApp.collectiontutorial;

import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetTest {
    public static void main(String[] args) {
        //Comparator and Comparable
        // Create a sorted set of some names
//        SortedSet<String> sortedNames = new TreeSet<>();
//        sortedNames.add("John");
//        sortedNames.add("Adam");
//        sortedNames.add("Eve");
//        sortedNames.add("Donna");
//        // Print the sorted set of names
//        System.out.println(sortedNames);
        SortedSet<Integer> sortedNames = new TreeSet<>();
        sortedNames.add(1);
        sortedNames.add(4);
        sortedNames.add(3);
        System.out.println(sortedNames);
    }
}