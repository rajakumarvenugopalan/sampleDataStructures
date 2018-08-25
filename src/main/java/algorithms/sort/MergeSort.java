package algorithms.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort<T extends Comparable> {

    public List<T> sort(List<T> input) {
        if(input == null || input.size() == 0)
        {
            return new ArrayList<>();
        }
        T[] inputArray = (T[])input.toArray();
        return Arrays.asList(splitTheArray(inputArray));
    }

    private T[] splitTheArray(T[] input) {
        if(input.length > 1) {
            int halfLength = input.length / 2;
            T[] array1 = (T[]) Array.newInstance(Comparable.class, halfLength);
            T[] array2 = (T[]) Array.newInstance(Comparable.class, input.length - halfLength);
            System.arraycopy(input, 0, array1, 0, halfLength);
            System.arraycopy(input, halfLength, array2, 0, input.length - halfLength);
            return mergeTheSortedArrays(splitTheArray(array1), splitTheArray(array2));
        }
        else {
            return input;
        }
    }

    private T[] mergeTheSortedArrays(T[] input1, T[] input2) {
        T[] output  = (T[]) Array.newInstance(Comparable.class, input1.length + input2.length);
        int x = 0, y = 0, numCopied = 0;
        while(x < input1.length || y < input2.length) {
            if(x >= input1.length && y < input2.length) {
                output[numCopied++] = input2[y++];
            }
            if(y >= input2.length && x < input1.length) {
                output[numCopied++] = input1[x++];
            }
            if(x < input1.length && y < input2.length)
            {
                int compareResult = input1[x].compareTo(input2[y]);
                output[numCopied++] = compareResult > 0 ? input2[y++] : input1[x++];
            }
        }
        return output;
    }
}
