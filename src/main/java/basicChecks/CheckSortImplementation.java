package basicChecks;

import algorithms.sort.HeapSort;
import algorithms.sort.MergeSort;
import algorithms.sort.ShellSort;
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
        System.out.println("Heap Ascending Order --> " + heapSort1.sortTheItems(inputList));
        System.err.println("Heap Descending Order --> " + heapSort2.sortTheItems(inputList));

        System.out.println("------------------------------------------------");
        System.out.println("                Merge Sort                      ");
        System.out.println("------------------------------------------------");
        MergeSort<Integer> mergeSort = new MergeSort<>();
        System.out.println("Merge Sort --> "+mergeSort.sort(inputList));

        System.out.println("------------------------------------------------");
        System.out.println("                Shell Sort                      ");
        System.out.println("------------------------------------------------");
        ShellSort<Integer> shellSort1 = new ShellSort<>();
        ShellSort<Integer> shellSort2 = new ShellSort<>(ShellSort.SORT_ORDER.DESCENDING_ORDER);
        System.out.println("Shell Ascending Order --> " + Arrays.asList(shellSort1.sortTheItems(new Integer[]{5, 10, 3, 20, 40, 1, 0})));
        System.err.println("Shell Descending Order --> " + Arrays.asList(shellSort2.sortTheItems(new Integer[]{5, 10, 3, 20, 40, 1, 0})));
    }
}
