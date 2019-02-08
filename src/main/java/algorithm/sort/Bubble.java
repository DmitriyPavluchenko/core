package algorithm.sort;

public class Bubble {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j < array.length - 1; j++) {
                if (array[i] > array[j + 1]) {
                    int t = array[i];
                    array[i] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }
}
