package com.example.HomeLoanApp.collectiontutorial;

import java.util.*;

public class CollectionsTest {


    //Generic

    //Changing the order of the list
    //swap , sort, shuffle, reverse, rotate
    public static void main(String[] args) {


        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(8);
        list.add(6);
        list.add(3);
        list.add(9);
        System.out.println(list);
//        Collections.sort(list);
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//        System.out.println("After sorting"+list);
//
//       int max = Collections.max(list);
//        System.out.println(max);
//        int min = Collections.min(list);
//        System.out.println(min);
//
////searching
//
//     int index=  Collections.binarySearch(list,4);
//        System.out.println(index);
//
////        Collections.shuffle(list);
////
////        System.out.println(list);
//
//        Collections.reverse(list);
//        System.out.println(list);
//
//        Collections.swap(list,1,3);
//        System.out.println(list);
//        Collections.rotate(list,2);
//        System.out.println(list);
//
//        Collections.fill(list,7);
//        System.out.println(list);
        List<Integer> list1 = new ArrayList<>();
        list1.add(8);
        list1.add(6);
        list1.add(3);
//        Collections.copy(list,list1);
//        System.out.println("copy"+list1);
//        Collections.replaceAll(list,7,1);
//        System.out.println(list);
        System.out.println(list1);
       int indexofS= Collections.indexOfSubList(list,list1);
        System.out.println(indexofS);
        int last= Collections.lastIndexOfSubList(list,list1);
        System.out.println(last);

      List<Integer> unmodified =  Collections.unmodifiableList(list);
        System.out.println("unmodified list:"+unmodified);
        unmodified.add(11);
        System.out.println(unmodified);
        ;

    }


}
