package classes;

import java.util.Objects;

public class MaximumContigiousSubsequence {
    public static Double[] act(Double[] sequence) {
        double maxTotal = 0;
        double currentTotal = 0;
        int currentFirstIndex = 0;
        int maxFirstIndex = 0;
        int maxLastIndex = 0;
        for (int i = 0; i < sequence.length; i++) {
            double current = sequence[i];
            if (current < 0) {
                if (currentTotal > maxTotal) {
                    maxFirstIndex = currentFirstIndex;
                    maxLastIndex = i - 1;
                    maxTotal = currentTotal;
                    currentTotal = 0;
                }
                currentFirstIndex = i + 1;
            } else {
                currentTotal += current;
            }
            if (i == sequence.length - 1) {
                if (currentTotal > maxTotal) {
                    maxFirstIndex = currentFirstIndex;
                    maxLastIndex = i;
                }
            }
        }
        int copyLength = maxLastIndex - maxFirstIndex + 1;
        Double[] returnValue = new Double[copyLength];
        System.arraycopy(sequence, maxFirstIndex, returnValue, 0, copyLength);
        return returnValue;
    }

    public static <T> void printArray(T[] array) {
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(" ]");
    }

    public static void main(String[] args) {
        Double[] test = {9.0, 2.0, 3.0, 4.0, -2.0, 100035.0, -5.0, 0.0, 105.0, 0.0, 3.0, 9.0, -798.0, 8.0, 4.0,
                0.0, -4.0, 0.0, 8.0, 0.0, 27897362.0, 2.0, 3.0, 45.0};
        printArray((Double[]) act(test));
    }
}
