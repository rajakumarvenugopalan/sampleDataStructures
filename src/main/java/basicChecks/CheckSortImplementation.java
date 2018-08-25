package basicChecks;

import algorithms.sort.HeapSort;
import algorithms.sort.MergeSort;
import dataStructures.MinAndMaxHeap;
import exceptions.DSStandardException;

import java.util.Arrays;
import java.util.List;

public class CheckSortImplementation {

    public static void main(String... args) throws DSStandardException {
        HeapSort<Integer> heapSort1 = new HeapSort<>(MinAndMaxHeap.SORT_TYPE.MIN_HEAP);
        HeapSort<Integer> heapSort2 = new HeapSort<>(MinAndMaxHeap.SORT_TYPE.MAX_HEAP);
        List<Integer> inputList = Arrays.asList(5, 10, 3, 20, 40, 1, 0);
        System.out.println("------------------------------------------------");
        System.out.println("                 Heap Sort                      ");
        System.out.println("------------------------------------------------");
        System.out.println("Ascending Order --> " + heapSort1.sortTheItems(inputList));
        System.err.println("Descending Order --> " + heapSort2.sortTheItems(inputList));

        System.out.println("------------------------------------------------");
        System.out.println("                Merge Sort                      ");
        System.out.println("------------------------------------------------");
        MergeSort<Integer> mergeSort = new MergeSort<>();
        System.out.println("Merge Sort --> "+mergeSort.sort(inputList));
    }
}
