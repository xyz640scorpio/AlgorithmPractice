import java.util.*;
public class largestSquareSurrondedBy1s {
    public int largestSquareSurroundedByOne(int[][] matrix) {
        // corner case
        int m = matrix.length; // m is row number
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length; // n is column number
        if (n == 0) {
            return 0;
        }

        // step 1: preprocess consecutive 1s
        int[][] right = new int[m][n]; // longest consecutive 1s from right to left
        int[][] down = new int[m][n]; // longest consecutive 1s from down to up

        for (int i = 0; i < m; i++)  {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (j == n - 1) {
                        right[i][j] = 1; // base case
                    } else {
                        right[i][j] = right[i][j + 1] + 1;
                    }
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                if (matrix[i][j] == 1) {
                    if (i == m - 1) {
                        down[i][j] = 1; // base case
                    } else {
                        down[i][j] = down[i + 1][j] + 1;
                    }
                }
            }
        }
        print2DArray(right);
        print2DArray(down);

        // step 2: traverse every possible left up corner of subsquare
        for (int len = Math.min(m, n); len >= 1; len--) {
            for (int i = 0; i < m - len + 1; i++) {
                for (int j = 0; j < n - len + 1; j++) {
                    // left up corner [i, j] right up corner [i][j + len - 1] left down corner [i + len - 1][j]
                    if (right[i][j] >= len && down[i][j] >= len && right[i + len - 1][j] >= len && down[i][j + len - 1] >= len) {
                        return len;
                    }

                }
            }
        }
        return 0;
    }

    public void print2DArray(int[][] matrix) {
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,1},{0,1},{1,1},{1,1},{1,0},{1,0}};
        largestSquareSurrondedBy1s o = new largestSquareSurrondedBy1s();
        System.out.print(o.largestSquareSurroundedByOne(matrix));
    }
}