package algorithms.checkDataStructures;

import algorithms.HeapUsingArrayList;
import algorithms.StackQueueUsingLinkedList;
import algorithms.exceptions.DSStandardException;

public class CheckDataStructure {

    public static void main(String... args) throws DSStandardException, InterruptedException {
        StackQueueUsingLinkedList<Integer> stackQueueUsingLinkedList1 = new StackQueueUsingLinkedList<>();
        stackQueueUsingLinkedList1.push(5);
        stackQueueUsingLinkedList1.push(2);
        stackQueueUsingLinkedList1.push(3);
        stackQueueUsingLinkedList1.push(4);
        stackQueueUsingLinkedList1.push(9);
        stackQueueUsingLinkedList1.push(7);
        stackQueueUsingLinkedList1.push(5);
        stackQueueUsingLinkedList1.push(0);

        System.out.println("Getting Elements from Queue");
        while(stackQueueUsingLinkedList1.peek() != null)
        {
            System.out.println(stackQueueUsingLinkedList1.poll());
        }

        Thread.sleep(1000);
        StackQueueUsingLinkedList<Integer> stackQueueUsingLinkedList2 = new StackQueueUsingLinkedList<>(StackQueueUsingLinkedList.TYPE.STACK);
        stackQueueUsingLinkedList2.push(5);
        stackQueueUsingLinkedList2.push(2);
        stackQueueUsingLinkedList2.push(3);
        stackQueueUsingLinkedList2.push(4);
        stackQueueUsingLinkedList2.push(9);
        stackQueueUsingLinkedList2.push(7);
        stackQueueUsingLinkedList2.push(5);
        stackQueueUsingLinkedList2.push(0);

        System.err.println("Getting Elements from Stack");
        while(stackQueueUsingLinkedList2.peek() != null)
        {
            System.out.println(stackQueueUsingLinkedList2.poll());
        }

        Thread.sleep(1000);
        HeapUsingArrayList<Integer> heapUsingArrayList = new HeapUsingArrayList<Integer>(HeapUsingArrayList.SORT_TYPE.MAX_HEAP);
        heapUsingArrayList.push(5);
        heapUsingArrayList.push(3);
        heapUsingArrayList.push(8);
        heapUsingArrayList.push(1);
        heapUsingArrayList.push(10);
        heapUsingArrayList.push(20);
        heapUsingArrayList.push(15);
        heapUsingArrayList.push(11);
        heapUsingArrayList.push(10);
        heapUsingArrayList.push(3);
        heapUsingArrayList.push(7);
        heapUsingArrayList.push(50);
        heapUsingArrayList.push(400);

        System.out.println(heapUsingArrayList);

        Integer object = null;
        StringBuilder sb = new StringBuilder();
        sb.append(heapUsingArrayList.toString() + "\n");
        while((object = heapUsingArrayList.pop()) != null)
        {
            System.out.println(object);
            sb.append(heapUsingArrayList.toString() + "\n");
        }
        Thread.sleep(1000);
        System.err.println(sb.toString());

    }
}
