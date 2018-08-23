package algorithms;

import java.util.ArrayList;
import java.util.List;

public class HeapUsingArrayList<T extends Comparable> {

    private List<T> inputItems = new ArrayList<>();
    private SORT_TYPE sortType;

    public HeapUsingArrayList() {
        this(SORT_TYPE.ASCENDING_ORDER);
    }

    public HeapUsingArrayList(SORT_TYPE sortType) {
        this.sortType = sortType;
    }

    public void push(T value) {
        inputItems.add(value);
        int size = inputItems.size();
        reheapifyBottomUp(size -1 , size);
    }

    public T pop() {
        if(inputItems.size() == 1) {
            return inputItems.remove(0);
        }
        else if(inputItems.size() > 0) {
            T itemToReturn = inputItems.get(0);
            T lastItem = inputItems.remove(inputItems.size() - 1);
            inputItems.set(0, lastItem);
            reheapifyTopDown(0, inputItems.size());
            return itemToReturn;
        }
        else {
            return null;
        }
    }

    enum SORT_TYPE {
        ASCENDING_ORDER (1),
        DESCENDING_ORDER (-1);

        int sortType;

        SORT_TYPE(int type) {
            this.sortType = type;
        }

        public int getSortType() {
            return sortType;
        }

        public void setSortType(int sortType) {
            this.sortType = sortType;
        }
    }


    private void reheapifyTopDown(int parentIndex, int totalElementsAvailable) {
        int leftChildIndex = parentIndex * 2 + 1;
        int rightChildIndex = parentIndex * 2 + 2;

        T parentElement = inputItems.get(parentIndex);
        T leftChild = leftChildIndex < totalElementsAvailable ? inputItems.get(leftChildIndex) : null;
        T rightChild = rightChildIndex < totalElementsAvailable ? inputItems.get(rightChildIndex) : null;

        int needToSwapElements = compareParentWithChilds(parentElement, leftChild, rightChild);

        if(needToSwapElements == 1)
        {
            swapElements(parentIndex, leftChildIndex);
            reheapifyTopDown(leftChildIndex, totalElementsAvailable);
        }
        else if(needToSwapElements == 2)
        {
            swapElements(parentIndex, rightChildIndex);
            reheapifyTopDown(rightChildIndex, totalElementsAvailable);
        }
    }

    private void reheapifyBottomUp(int currentElementIndex, int totalElementsAvailable) {
        int parentElementIndex, leftChildIndex = Integer.MAX_VALUE, rightChildIndex = Integer.MAX_VALUE;

        if(totalElementsAvailable == 1 || currentElementIndex == 0)
        {
            parentElementIndex = 0;
            return;
        }
        else if(totalElementsAvailable % 2 == 0)
        {
            leftChildIndex = currentElementIndex;
            parentElementIndex = (leftChildIndex - 1) / 2;
        }
        else {
            leftChildIndex = currentElementIndex;
            rightChildIndex = currentElementIndex - 1;
            parentElementIndex = (leftChildIndex - 1) / 2;
        }
        T parentElement = inputItems.get(parentElementIndex);
        T leftChild = leftChildIndex < totalElementsAvailable ? inputItems.get(leftChildIndex) : null;
        T rightChild = rightChildIndex < totalElementsAvailable ? inputItems.get(rightChildIndex) : null;

        int needToSwapElements = compareParentWithChilds(parentElement, leftChild, rightChild);

        if(needToSwapElements == 1)
        {
            swapElements(parentElementIndex, leftChildIndex);
            reheapifyBottomUp(parentElementIndex, totalElementsAvailable);
        }
        else if(needToSwapElements == 2)
        {
            swapElements(parentElementIndex, rightChildIndex);
            reheapifyBottomUp(parentElementIndex, totalElementsAvailable);
        }

    }

    private int compareParentWithChilds(T parent, T leftChild, T rightChild)
    {
        if(leftChild == null && rightChild == null)
        {
            return 0;
        }
        else if(rightChild == null)
        {
            return parent.compareTo(leftChild) * this.sortType.getSortType() < 0 ? 0 : 1;
        }
        else
        {
            return leftChild.compareTo(rightChild) * this.sortType.getSortType() < 0 ?
                    parent.compareTo(leftChild) * this.sortType.getSortType() < 0 ? 0 : 1 :
                    parent.compareTo(rightChild) * this.sortType.getSortType() < 0 ? 0 : 2;
        }
    }

    private void swapElements(int parentIndex, int childIndex)
    {
        T parentElement = inputItems.get(parentIndex);
        T childElement = inputItems.get(childIndex);
        inputItems.set(parentIndex, childElement);
        inputItems.set(childIndex, parentElement);
    }

    public String toString()
    {
        if(inputItems == null)
            return "No Elements";
        else
            return inputItems.toString();
    }

    public static void main(String... args) throws InterruptedException{
        HeapUsingArrayList<Integer> heapUsingArrayList = new HeapUsingArrayList<Integer>(SORT_TYPE.DESCENDING_ORDER);
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