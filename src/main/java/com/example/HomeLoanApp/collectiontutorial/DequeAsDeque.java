package com.example.HomeLoanApp.collectiontutorial;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class DequeAsDeque {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("John");
        deque.addLast("Man");
        deque.offerLast("Ken");
        System.out.println(deque);

//        while(deque.peekFirst() != null){
//            System.out.println("Head Element   "+ deque.peekFirst());
//            deque.removeFirst();
//            System.out.println("Deque"+ deque);
//        }

        while (deque.peekLast() != null){
            System.out.println("Tail Element:  "+ deque.peekLast());
            deque.removeLast();
            System.out.println("Deque: "+ deque);
        }
        System.out.println("deque is empty: "+deque.isEmpty());
        System.out.println("Peek first: "+ deque.peekFirst());
        System.out.println("Poll first: "+ deque.pollFirst());

        try {
            String str = deque.getFirst();
            System.out.println("dequte get first :" + str);
        }
        catch (NoSuchElementException e){
            System.out.println("Deque is empty");
        }

    }
}
