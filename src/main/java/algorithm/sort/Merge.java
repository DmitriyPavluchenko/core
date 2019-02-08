package algorithm.sort;

public class Merge {
    public static int[] sort(int[] array) {
        if (array.length == 1) return array;
        ArrayPart arrayPart = split(array);
        return merge(sort(arrayPart.left), sort(arrayPart.right));
    }

    private static ArrayPart split(int[] array) {
        int middle;
        int rLength;
        if (array.length % 2 == 0) {
            middle = array.length / 2;
            rLength = middle;
        } else {
            middle = array.length / 2 + 1;
            rLength = middle - 1;
        }
        int[] left = new int[middle];
        int[] right = new int[rLength];
        System.arraycopy(array, 0, left, 0, middle);
        System.arraycopy(array, middle, right, 0, rLength);
        return new ArrayPart(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int lCursor = 0;
        int rCursor = 0;
        int[] res = new int[left.length + right.length];
        for (int i = 0; i < res.length; i++) {
            if (lCursor < left.length && rCursor < right.length) {
                if (left[lCursor] < right[rCursor]) {
                    res[i] = left[lCursor];
                    lCursor++;
                } else {
                    res[i] = right[rCursor];
                    rCursor++;
                }
            } else if (lCursor == left.length) {
                res[i] = right[rCursor];
                rCursor++;
            } else if (rCursor == right.length) {
                res[i] = left[lCursor];
                lCursor++;
            }
        }
        return res;
    }

    private static class ArrayPart {
        int[] left;
        int[] right;

        ArrayPart(int[] left, int[] right) {
            this.left = left;
            this.right = right;
        }
    }
}
