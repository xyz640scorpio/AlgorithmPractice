import java.io.*;
public class SnowFlake {

    public int minimumOperations(int[] nums) {
        int[] container = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
           break;
        }
        return 0;
    }

    // find the maximum size of square sub-grid such that all sub-grids of this size < maxSum
    public int largestSubGrid(int[][] grid, int maxSum) {
        int[][] prefixSums = new int[grid.length][grid[0].length];
        // build up prefixSum matrix
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    prefixSums[i][j] = grid[i][j];
                } else if (i == 0) {
                    prefixSums[i][j] = prefixSums[i][j - 1] + grid[i][j];
                } else if (j == 0){
                    prefixSums[i][j] = prefixSums[i - 1][j] + grid[i][j];
                } else {
                    prefixSums[i][j] = prefixSums[i - 1][j] + prefixSums[i][j - 1] - prefixSums[i - 1][j - 1] + grid[i][j];
                }
            }
        }
        for (int size = grid.length; size > 0; size--) {
            int curMax = 0;
            for (int i = 0; i + size - 1 < grid.length; i++) {
                for (int j = 0; j + size - 1 < grid.length; j++) {
                    if (size == 1) {
                        curMax = Math.max(curMax, grid[i][j]);
                    } else {
                        curMax = Math.max(curMax, prefixSums[i + size - 1][j + size - 1] - prefixSums[i + size - 1][j] - prefixSums[i][j + size - 1] + prefixSums[i][j]);
                    }
                }
            }
            if (curMax <= maxSum) {
                System.out.printf("curMax: %d \t maxSum: %d\n", curMax, maxSum);
                return size;
            }
        }
        // no square sub-grid satisfies the condition
        return 0;
    }

    public static void main(String[] args) {
        SnowFlake o = new SnowFlake();
        int[][] grid = new int[3][3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = i + 2;
                System.out.print(grid[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }
        int[] maxSums = new int[]{2, 4, 10, 14, 26, 27, 200};
        for (int maxSum: maxSums) {
            System.out.printf("max sub-grid size: %d\n", o.largestSubGrid(grid, maxSum));
        }
    }
}
