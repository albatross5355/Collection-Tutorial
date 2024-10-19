package com.example.HomeLoanApp.collectiontutorial;

import java.util.HashSet;
import java.util.Set;

public class SetTutorial {

    public static void main(String[] args) {
//        addInSet();
        inmutableset();
    }

    public static void addInSet(){
        //un ordered collection
        Set<String> names = new HashSet<>();
        names.add("Ken");
        names.add("Lee");
        names.add("Joe");
        //It will  not have any effect
        names.add("Joe");
        names.add(null);
        System.out.println(names);
    }


    public  static void inmutableset(){

        //On creating null set we cannot add null and also duplicate element
        Set<String> names = Set.of("John","Kee","Lee");
        System.out.println(names);
        names.forEach(System.out::println);
        //When try to add UnsupportedOperationException will occur
//        names.add("Ken");
//        System.out.println(names);

    }
}
