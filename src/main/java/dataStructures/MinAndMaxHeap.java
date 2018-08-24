package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class MinAndMaxHeap<T extends Comparable> {

    private List<T> inputItems = new ArrayList<>();
    private SORT_TYPE sortType;
    private static final Integer NO_NEED_TO_SWAP = 0;
    private static final Integer SWAP_PARENT_WITH_LEFT = 1;
    private static final Integer SWAP_PARENT_WITH_RIGHT = 2;

    /**
     * By Default create a Min Heap
     *
     * */
    public MinAndMaxHeap() {
        this(SORT_TYPE.MIN_HEAP);
    }

    /**
     * Create a Min or Max Heap based on the argument passed
     *
     * */
    public MinAndMaxHeap(SORT_TYPE sortType) {
        this.sortType = sortType;
    }

    /**
     * @Input value
     * @Output void
     * Adds an element to the Heap at the Bottom and rearranges the Items till Heap conditions are satisfied
     *
     * */
    public void push(T value) {
        inputItems.add(value);
        int size = inputItems.size();
        reheapifyBottomUp(size -1 , size);
    }

    /**
     * @Output value of the top Element
     * Returns the top element and replaces it with the bottom most element. Rearranges the items until the Heap conditions are satisfied
     *
     * */
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

    /**
     * Min or Max Heap Enum
     *
     * */

    public enum SORT_TYPE {
        MIN_HEAP (1),
        MAX_HEAP (-1);

        int sortType;

        SORT_TYPE(int type) {
            this.sortType = type;
        }

        public int getSortType() {
            return sortType;
        }

    }

    /**
     * This method is used to check if the elements are satisfying the Heap conditions starting from root element and
     * navigates until Heap conditions are satisfied with it's children or until the last element is reached.
     * This is called when an element is popped.
     *
     * */
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

    /**
     * This method is used to check if the elements are satisfying the Heap conditions starting the recently added element
     * and navigates until a Parent satisfies Heap conditions with it's children or root is reached.
     * This is called when an element is popped.
     *
     * */
    private void reheapifyBottomUp(int currentElementIndex, int totalElementsAvailable) {
        int parentElementIndex, leftChildIndex, rightChildIndex = Integer.MAX_VALUE;

        if(totalElementsAvailable == 1 || currentElementIndex == 0)
        {
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
            return NO_NEED_TO_SWAP;
        }
        else if(rightChild == null)
        {
            return parent.compareTo(leftChild) * this.sortType.getSortType() < 0 ? NO_NEED_TO_SWAP : SWAP_PARENT_WITH_LEFT;
        }
        else
        {
            return leftChild.compareTo(rightChild) * this.sortType.getSortType() < 0 ?
                    parent.compareTo(leftChild) * this.sortType.getSortType() < 0 ? NO_NEED_TO_SWAP : SWAP_PARENT_WITH_LEFT :
                    parent.compareTo(rightChild) * this.sortType.getSortType() < 0 ? NO_NEED_TO_SWAP : SWAP_PARENT_WITH_RIGHT;
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
}
