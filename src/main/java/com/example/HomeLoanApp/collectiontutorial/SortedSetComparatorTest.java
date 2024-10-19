package com.example.HomeLoanApp.collectiontutorial;


import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

    public class SortedSetComparatorTest {
        public static void main(String[] args) {
            // Create a sorted set sorted by id
            SortedSet<Person> personsById
                    = new TreeSet<>(Comparator.comparing(Person::getId));

            // Add soem persons to the set
            personsById.add(new Person(1, "John"));
            personsById.add(new Person(2, "Adam"));
            personsById.add(new Person(3, "Eve"));
            personsById.add(new Person(4, "Donna"));
            personsById.add(new Person(4, "Donna")); // A duplicate Person

            // Print the set
            System.out.println("People sorted by id:");
            personsById.forEach(System.out::println);

            // Create a sorted set sorted by name
            SortedSet<Person> personsByName
                    = new TreeSet<>(Comparator.comparing(Person::getName));
            personsByName.add(new Person(1, "John"));
            personsByName.add(new Person(2, "Adam"));
            personsByName.add(new Person(3, "Eve"));
            personsByName.add(new Person(4, "Donna"));
            personsByName.add(new Person(4, "Kip")); // Not a duplicate person

            System.out.println("\nPeople sorted by name: ");
            personsByName.forEach(System.out::println);



            //Suppose their is a group of strings and we need to remove duplicates and also sort in ascending of length
            // John , Adam , Eve, John, Eve
            //John , Adam , Eve
            //Duplicate using set
            //Sorted set it can use to sort in ascending order

            SortedSet<String> sortedSet = new TreeSet<>(Comparator.comparing(String::length));
            sortedSet.add("John");
            sortedSet.add("Adamm");
            sortedSet.add("Eve");
            sortedSet.add("John");
            sortedSet.add("Eve");
            sortedSet.forEach(System.out::println);


        }
    }

