package algorithms.sort;

import dataStructures.MinAndMaxHeap;
import dataStructures.exceptions.DSStandardException;

import java.util.LinkedList;
import java.util.List;

public class HeapSort<T extends Comparable> {

    private final MinAndMaxHeap.SORT_TYPE sortType;

    public HeapSort(MinAndMaxHeap.SORT_TYPE sortType) {
        this.sortType = sortType;
    }

    public List<T> sortTheItems(List<T> inputList) throws DSStandardException {
        if(inputList == null) {
            throw new DSStandardException("Can't sort the Null List");
        }
        else {
            MinAndMaxHeap<T> minAndMaxHeap = new MinAndMaxHeap<>(sortType);
            List<T> outputList = new LinkedList<>();
            for(T input: inputList)
                minAndMaxHeap.push(input);

            T outputItem = null;
            while((outputItem = minAndMaxHeap.pop()) != null)
            {
                outputList.add(outputItem);
            }
            return outputList;
        }
    }
}
