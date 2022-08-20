import java.util.*;
public class minDiffTwoSubsets {

    int minDiff = Integer.MAX_VALUE;

    public int minDifference(int[] array) {
        // corner case
        int total = 0;
        for (int num: array) {
            total += num;
        }
        int[] cur = new int[array.length];
        helper(array, 0, 0, total, cur);
        return minDiff;
    }

    private void helper(int[] array, int index, int subset, int total, int[] cur) {
        // base case
        if (index == array.length) {
            return;
        }

        // case 1: add array value on current index
        subset += array[index];
        cur[index] = array[index];
        if (Math.abs(total - 2 * subset) < minDiff) {
            minDiff = Math.abs(total - 2 * subset);
        }
        System.out.print("Current subset: \t\t");
        System.out.println(Arrays.toString(cur));
        System.out.print("global minDiff and subset sum:\t");
        System.out.print(minDiff);
        System.out.print("\t");
        System.out.println(subset);
        int cur_sum = 0;
        for (int i = 0; i < cur.length; i++) {
            cur_sum += cur[i];
        }
        System.out.print("Current sum:\t");
        System.out.println(cur_sum);
        helper(array, index + 1, subset, total, cur);
        cur[index] = 0;
        subset -= array[index];
        // case 2: not add array value on current index
        if (Math.abs(total - 2 * subset) < minDiff) {
            minDiff = Math.abs(total - 2 * subset);
        }
        System.out.print("Current subset: \t\t");
        System.out.println(Arrays.toString(cur));
        System.out.print("global minDiff and subset sum:\t");
        System.out.print(minDiff);
        System.out.print("\t");
        System.out.println(subset);
        cur_sum = 0;
        for (int i = 0; i < cur.length; i++) {
            cur_sum += cur[i];
        }
        System.out.print("Current sum:\t");
        System.out.println(cur_sum);
        helper(array, index + 1, subset, total, cur);
    }

    public static void main(String[] args) {
        minDiffTwoSubsets o = new minDiffTwoSubsets();
        o.minDifference(new int[]{-2,-9,-3,-1,-1});
    }
}
