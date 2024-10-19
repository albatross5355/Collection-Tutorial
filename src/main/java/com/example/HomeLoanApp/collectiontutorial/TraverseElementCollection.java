package com.example.HomeLoanApp.collectiontutorial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TraverseElementCollection {
    public static void main(String[] args) {

//        int var =  add(2,3);
//        System.out.println(var);
//     iterateList();
//     removeElements();
//        foreachRemainingMethod();
//        forEachLoop();
        forEachMethod();
    }

// Iterator
    public static void iterateList() {
        List<String> names = new ArrayList<>();
        // Add some names to the list
        names.add("Ken");
        names.add("Lee");
        names.add("Joe");
// Get an iterator for the list

        Iterator<String> nameIterator = names.iterator();
        System.out.println(nameIterator.getClass());
// Iterate over all elements in the list
        while(nameIterator.hasNext()) {
            // Get the next element from the list
            String name = nameIterator.next();
            // Print the name
            System.out.println(name);
        }
        System.out.println();
    }


    public static void removeElements() {
        List<String> names = new ArrayList<>();
        names.add("Ken");
        names.add("Lee");
        names.add("Joe");
        names.add("Je");

// Get an iterator for the list
        Iterator<String> nameIterator = names.iterator();
// Iterate over all elements in the list
        while (nameIterator.hasNext()) {
            String name = nameIterator.next();
            // Remove the name if it is two characters
            if (name.length() == 3) {
                nameIterator.remove();
            }
        }

        System.out.println(names);
    }

    public static  void foreachRemainingMethod(){
        List<String> names = new ArrayList<>();
        names.add("Ken");
        names.add("Lee");
        names.add("Joe");
        names.add("Je");
        names.remove(0);

// Get an iterator for the list
        Iterator<String> nameIterator = names.iterator();
        while (nameIterator.hasNext()) {
            String name = nameIterator.next();
            // Remove the name if it is two characters
            if (name.length() == 3) {
                nameIterator.remove();
            }
        }
        Iterator<String> nameIterator1 = names.iterator();

        nameIterator1.forEachRemaining(System.out::println);
    }

    //Perform iterator over collection using threads


    public  static void forEachLoop(){
        //U can use this with every collection that implements collection interface but not wiyth map
        List<String> names = new ArrayList<>();
        names.add("Ken");
        names.add("Lee");
        names.add("Joe");
        names.add("Je");

        for(String name: names){
            System.out.println(name);
        }
//Limitation is that it can not call it while iterator can
        for(String name: names){
            names.remove(name);
        }
    }
   public static void forEachMethod(){

       List<String> names = new ArrayList<>();
       names.add("Ken");
       names.add("Lee");
       names.add("Joe");
       names.add("Je");
       names.forEach(System.out::println);
   }

}
