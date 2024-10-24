package com.example.HomeLoanApp.collectiontutorial;

import java.util.Deque;
import java.util.LinkedList;

public class ArrayDeque {
    public static void main(String[] args) {
        Deque<String> deque = new java.util.ArrayDeque<>();
        deque.push("John");
        deque.push("Man");
        deque.push("Ken");
        System.out.println(deque);

        while(deque.peek()!= null){
            System.out.println("element at top :"+ deque.peek());
            System.out.println("Removed top element: "+ deque.pop());
            System.out.println(deque);
        }
        System.out.println("Deque is empty :"+ deque.isEmpty());
    }
}
