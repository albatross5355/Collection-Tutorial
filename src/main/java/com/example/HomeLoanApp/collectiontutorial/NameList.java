package com.example.HomeLoanApp.collectiontutorial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
public class NameList {

    public static void main(String[] args) {
        // Create a list of strings
        List<String> names = new ArrayList<>();
        // Print the list details
        System.out.printf("After creation: Size = %d, Elements = %s%n",
                names.size(), names);
        // Add some names to the list
        names.add("Ken");
        names.add("Le");
        names.add("Joe");
        // Print the list details
        System.out.printf("After adding 3 elements: Size = %d, Elements = %s%n",
                names.size(), names);
        // Remove Lee from the list
        names.remove("Lee");
        names.get(0);

        System.out.println(names.get(0));
        // Print the list details
        System.out.printf("After removing 1 element: Size = %d, Elements = %s%n",
                names.size(), names);
        // Clear all elements
        names.clear();
        // Print the list details
        System.out.printf("After clearing all elements: Size = %d, Elements = %s%n",
                names.size(), names);

        // Iteration are of three types
        //1. Using iterator
        Iterator<String> iterator = names.iterator();
        while(iterator.hasNext()){
            String name = iterator.next();
            System.out.println("Name:"+name);
        }

        Iterator<String> iterator1 = names.iterator();
        while(iterator1.hasNext()){
            String name = iterator1.next();
            if (name.length() == 2) {
                System.out.println("Remove method:"+name);
                iterator1.remove();
            }
        }

        Iterator<String> iterator2 = names.iterator();
        iterator2.forEachRemaining(System.out::println);

        //Using for each loop
        for(String name: names){
            System.out.println(name);
        }


        //using foreach method of iterable interface
        names.forEach((System.out::println));

    }
}
