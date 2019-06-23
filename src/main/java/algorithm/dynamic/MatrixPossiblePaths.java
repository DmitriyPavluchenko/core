package algorithm.dynamic;

import java.util.Arrays;

public class MatrixPossiblePaths {

    public static int calculatePaths(int n, int m) {
        if (n == 1 || m == 1) return 1;
        int[][] array = new int[n][m];
        array[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) array[i][j] = 1;
                else array[i][j] = array[i - 1][j] + array[i][j - 1];
            }
        }

        return array[n - 1][m - 1];
    }

    public static int findCheapestPath(int[][] array, int targetN, int targetM) {
        int[][] pathWeight = Arrays.copyOf(array, array.length);
        pathWeight[0][0] = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i > 0 && j > 0) {
                    pathWeight[i][j] = Math.min(pathWeight[i][j - 1], pathWeight[i - 1][j]) + array[i][j];
                } else if (i > 0) {
                    pathWeight[i][j] = pathWeight[i - 1][j] + array[i][j];
                } else if (j > 0) {
                    pathWeight[i][j] = pathWeight[i][j - 1] + array[i][j];
                }
            }
        }
        return pathWeight[targetN - 1][targetM - 1];
    }
}
