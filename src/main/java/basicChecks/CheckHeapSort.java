package basicChecks;

import algorithms.sort.HeapSort;
import dataStructures.MinAndMaxHeap;
import dataStructures.exceptions.DSStandardException;

import java.util.Arrays;
import java.util.List;

public class CheckHeapSort {

    public static void main(String... args) throws DSStandardException {
        HeapSort<Integer> heapSort1 = new HeapSort<>(MinAndMaxHeap.SORT_TYPE.MIN_HEAP);
        HeapSort<Integer> heapSort2 = new HeapSort<>(MinAndMaxHeap.SORT_TYPE.MAX_HEAP);
        List<Integer> inputList = Arrays.asList(5, 10, 3, 20, 40, 1, 0);
        System.out.println("Ascending Order --> " + heapSort1.sortTheItems(inputList));
        System.err.println("Descending Order --> " + heapSort2.sortTheItems(inputList));
    }
}
