import java.util.*;
public class largestX1s {
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
        int[][] M1 = new int[m][n]; // left corner to right corner
        int[][] M2 = new int[m][n]; // reverse
        int[][] M3 = new int[m][n]; // right corner to left corner
        int[][] M4 = new int[m][n]; // reverse

        // left up corner to right down corner
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) { // base case
                        M1[i][j] = 1;
                    } else {
                        M1[i][j] = M1[i - 1][j - 1] + 1;
                    }
                }
            }
        }
        // right down corner to left up corner
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j>= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i == m - 1 || j == n - 1) {
                        M2[i][j] = 1;
                    } else {
                        M2[i][j] = M2[i + 1][j + 1] + 1;
                    }
                }
            }
        }
        // right up corner to left down corner
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j>= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == n - 1) {
                        M3[i][j] = 1;
                    } else {
                        M3[i][j] = M3[i - 1][j + 1] + 1;
                    }
                }
            }
        }
        // left down corner to right up corner
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == m - 1 || j == 0) {
                        M4[i][j] = 1;
                    } else {
                        M4[i][j] = M4[i + 1][j - 1] + 1;
                    }
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
        int[][] matrix = new int[][]{{0,1,1,0,1},{1,1,1,1,0},{0,0,1,0,1}};
        largestX1s o = new largestX1s();
        System.out.print(o.largest(matrix));
    }
}

// Time Complexity: O(n^3) -- O(5*n^2)
// Space Complexity: O(n^2)