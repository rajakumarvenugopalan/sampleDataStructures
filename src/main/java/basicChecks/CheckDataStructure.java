package basicChecks;

import dataStructures.MinAndMaxHeap;
import dataStructures.StackAndQueue;
import dataStructures.exceptions.DSStandardException;

public class CheckDataStructure {

    public static void main(String... args) throws DSStandardException, InterruptedException {
        StackAndQueue<Integer> stackAndQueue1 = new StackAndQueue<>();
        stackAndQueue1.push(5);
        stackAndQueue1.push(2);
        stackAndQueue1.push(3);
        stackAndQueue1.push(4);
        stackAndQueue1.push(9);
        stackAndQueue1.push(7);
        stackAndQueue1.push(5);
        stackAndQueue1.push(0);

        System.out.println("Getting Elements from Queue");
        while(stackAndQueue1.peek() != null)
        {
            System.out.println(stackAndQueue1.poll());
        }

        Thread.sleep(1000);
        StackAndQueue<Integer> stackAndQueue2 = new StackAndQueue<>(StackAndQueue.TYPE.STACK);
        stackAndQueue2.push(5);
        stackAndQueue2.push(2);
        stackAndQueue2.push(3);
        stackAndQueue2.push(4);
        stackAndQueue2.push(9);
        stackAndQueue2.push(7);
        stackAndQueue2.push(5);
        stackAndQueue2.push(0);

        System.err.println("Getting Elements from Stack");
        while(stackAndQueue2.peek() != null)
        {
            System.out.println(stackAndQueue2.poll());
        }

        Thread.sleep(1000);
        MinAndMaxHeap<Integer> minAndMaxHeap = new MinAndMaxHeap<Integer>(MinAndMaxHeap.SORT_TYPE.MAX_HEAP);
        minAndMaxHeap.push(5);
        minAndMaxHeap.push(3);
        minAndMaxHeap.push(8);
        minAndMaxHeap.push(1);
        minAndMaxHeap.push(10);
        minAndMaxHeap.push(20);
        minAndMaxHeap.push(15);
        minAndMaxHeap.push(11);
        minAndMaxHeap.push(10);
        minAndMaxHeap.push(3);
        minAndMaxHeap.push(7);
        minAndMaxHeap.push(50);
        minAndMaxHeap.push(400);

        System.out.println(minAndMaxHeap);

        Integer object = null;
        StringBuilder sb = new StringBuilder();
        sb.append(minAndMaxHeap.toString() + "\n");
        while((object = minAndMaxHeap.pop()) != null)
        {
            System.out.println(object);
            sb.append(minAndMaxHeap.toString() + "\n");
        }
        Thread.sleep(1000);
        System.err.println(sb.toString());

    }
}
