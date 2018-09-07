package algorithms.sort;

import exceptions.DSStandardException;

public class ShellSort<T extends Comparable> {

    private final SORT_ORDER sortOrder;

    public ShellSort() {
        this(SORT_ORDER.ASCENDING_ORDER);
    }

    public ShellSort(SORT_ORDER sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Comparable[] sortTheItems(Comparable[] inputArray) throws DSStandardException {
        int length = inputArray.length;
        int sortSize = 1;

        while(sortSize < length / 3) {
            sortSize = sortSize * 3 + 1;
        }

        while(sortSize >= 1) {
            for(int x = sortSize; x < length; x++) {
                for(int y = x; y >= sortSize; y -= sortSize) {
                    if(compareResult(inputArray[y-sortSize], inputArray[y])) {
                        Comparable temp = inputArray[y];
                        inputArray[y] = inputArray[y-sortSize];
                        inputArray[y-sortSize] = temp;
                    }
                }
            }
            sortSize = sortSize / 3;
        }

        return inputArray;
    }

    public enum SORT_ORDER {
        ASCENDING_ORDER(1),
        DESCENDING_ORDER(-1);

        int order;

        SORT_ORDER(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }

    private boolean compareResult(Comparable a, Comparable b) {
        if(a.compareTo(b) * this.sortOrder.getOrder() > 0)
            return true;
        else
            return false;
    }
}
