import java.util.*;

public class largestCross1 {
    public int largest(int[][] matrix) {
        // corner case
        int m = matrix.length; // m is row number
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length; // n is column number
        if (n == 0) {
            return 0;
        }

        // step 1: preprocess the matrix into 4 ascending longest 1 matrix
        int[][] M1 = new int[m][n]; // left to right
        int[][] M2 = new int[m][n]; // right to left
        int[][] M3 = new int[m][n]; // up to down
        int[][] M4 = new int[m][n]; // down to up

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    M1[i][j] = matrix[i][j]; // base case
                } else if (matrix[i][j] == 1) {
                    M1[i][j] = M1[i][j - 1] + 1;
                } else {
                    M1[i][j] = 0;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (j == n - 1) {
                    M2[i][j] = matrix[i][j]; // base case
                } else if (matrix[i][j] == 1) {
                    M2[i][j] = M2[i][j + 1] + 1;
                } else {
                    M2[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (i == 0) {
                    M3[i][j] = matrix[i][j]; // base case
                } else if (matrix[i][j] == 1) {
                    M3[i][j] = M3[i - 1][j] + 1;
                } else {
                    M3[i][j] = 0;
                }
            }
            for (int i = m - 1; i >= 0; i--) {
                if (i == m - 1) {
                    M4[i][j] = matrix[i][j]; // base case
                } else if (matrix[i][j] == 1) {
                    M4[i][j] = M4[i + 1][j] + 1;
                } else {
                    M4[i][j] = 0;
                }
            }
        }
        print2DArray(M1);
        print2DArray(M2);
        print2DArray(M3);
        print2DArray(M4);

        // step 2: traverse al central position, find the max arm
        int largest = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                largest = Math.max(Math.min(M1[i][j], Math.min(M2[i][j], Math.min(M3[i][j], M4[i][j]))), largest);
            }
        }
        return largest;
    }

    public void print2DArray(int[][] matrix) {
        System.out.println(Arrays.deepToString(matrix));
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,1,1,0,1},{1,0,1,1,1},{1,1,1,1,1},{1,0,1,1,0},{0,0,1,1,0}};
        largestCross1 o = new largestCross1();
        System.out.print(o.largest(matrix));
    }
}

// Time Complexity: O(n^2) -- O(5*n^2)
// Space Complexity: O(n^2)
