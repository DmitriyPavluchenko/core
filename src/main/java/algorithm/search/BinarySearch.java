package algorithm.search;

public class BinarySearch {
    public static int search(int[] array, int searchElement) {
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int middle = (min + max) / 2;
            int midElem = array[middle];
            if (midElem > searchElement) {
                max = middle;
            } else if (midElem < searchElement) {
                min = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
