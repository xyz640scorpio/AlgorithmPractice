import java.util.*;
public class BlackCount {

    public static long[] blackCount(int rows, int cols, int[][] black) {
        long[] count = new long[5];
        // corner case
        if (black.length == 0) {
            count[0] = (long) (rows - 1) * (cols - 1);
            return count;
        }
        int[][] grid = new int[rows][cols];
        for (int[] cell: black) {
            grid[cell[0]][cell[1]] = 1;
        }
        int[][] prefixSum = new int[rows][cols];

        // n^2
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    prefixSum[i][j] = grid[i][j];
                } else if (i == 0) {
                    prefixSum[i][j] = prefixSum[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    prefixSum[i][j] = prefixSum[i - 1][j] + grid[i][j];
                } else {
                    prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + grid[i][j];
                }
            }
        }
        // i, j左上角, k t 右下角 k, j左下角，it右上角
        for (int i = 0; i <= rows - 2; i++) {
            for (int j = 0; j <= cols - 2; j++) {
                int k = i + 1;
                int t = j + 1;
                int size = prefixSum[k][t];
                if (i > 0) {
                    size -= prefixSum[i - 1][t];
                }
                if (j > 0) {
                    size -= prefixSum[k][j - 1];
                }
                if (i > 0 && j > 0) {
                    size += prefixSum[i - 1][j - 1];
                }
                count[size]++;
            }
        }
        return count;
    }

//    public static long[] blackCount(int rows, int cols, int[][] black) {
//
//    }

    public static void main(String[] args) {
        long[] res = blackCount(3, 3, new int[][]{{0, 0}, {0, 1}, {1, 0}});
        for (long num: res) {
            System.out.println(num);
        }
    }
}
