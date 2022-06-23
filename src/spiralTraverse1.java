import java.util.*;
public class spiralTraverse1 {
    public List<Integer> spiral(int[][] matrix) {
        // corner case
        if (matrix == null || matrix.length < 1) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        spiralHelper(matrix, 0, matrix.length, res);
        return res;
    }

    // recursion这里的物理意义: 一个level代表遍历一圈的matrix
    private void spiralHelper(int[][] matrix, int offset, int size, List<Integer> res) {
        // base case: 终止条件, 圈的size为1
        if (size <= 1) {
            res.add(matrix[offset][offset]);
            return;
        }
        // top left to top right
        for(int i = offset; i < offset + size - 1; i++) {
            res.add(matrix[offset][i]);
        }
        // top right to bottom right
        for (int i = offset; i < offset + size - 1; i++) {
            res.add(matrix[i][offset + size - 1]);
        }
        // bottom right to bottom left
        for (int i = offset + size - 1; i > offset; i--) {
            res.add(matrix[offset + size - 1][i]);
        }
        // bottom left to top left
        for (int i = offset + size - 1; i > offset; i--) {
            res.add(matrix[i][offset]);
        }
        spiralHelper(matrix, offset + 1, size - 2, res);
    }

    public static void main(String[] args) {
        spiralTraverse1 obj = new spiralTraverse1();
        // {-85,56,37,48,-25,-78,-29,62, 18,-60,-74,-84, 90,44,5,1};
        int[][] matrix = new int[4][4];
        System.out.print(obj.spiral(matrix));
    }
}