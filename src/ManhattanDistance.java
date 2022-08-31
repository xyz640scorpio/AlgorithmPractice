import java.util.Arrays;

public class ManhattanDistance {
    public int minCost(int[] x, int[] y) {
        int len = x.length;
        int minCost = 0;
        int sumY = 0;
        int sumX = 0;
        for (int i = 0; i < len; i++) {
            sumY += y[i];
        }
        int y_target = sumY / len;
        for (int i = 0; i < len; i++) {
            minCost += Math.abs(y[i] - y_target);
        }
        Arrays.sort(x);
        int minDiff = Integer.MAX_VALUE;
        for (int i = x[0]; i < x[len - 1]; i++) {
            int diff = 0;
            for (int j = 0; j < len; j++) {
                diff += Math.abs(i + j - x[j]);
            }
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        minCost += minDiff;
        return minCost;
    }
}

