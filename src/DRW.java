import java.util.*;

public class DRW {
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean[][] visited;
    public int[] solution(String[] B) {
        visited = new boolean[B.length][B[0].length()];
        int[] res = new int[3];
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length(); j++) {
                int count = 1;
                if (!visited[i][j] && B[i].charAt(j) == '#') {
                    int size = dfs(B, i, j, 0);
                    if (size <= 2) res[size]++;
                }
            }
        }
        return res;
    }

    private int dfs(String[] B, int i, int j, int count){
        visited[i][j] = true;
        // recursive cases
        for (int[] dir: dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if(nextI >= 0 && nextJ >= 0 && nextI < B.length && nextJ < B[0].length()
                    && !visited[nextI][nextJ] && B[nextI].charAt(nextJ) == '#') {
                return dfs(B, nextI, nextJ, count + 1);
            }
        }
        return count;
    }

    private char[] colors = {'R', 'G', 'B'};
    public String triColor(int[] A) {
        List<String> res = new ArrayList<>();
        int[] sum = new int[3];
        StringBuilder sb = new StringBuilder();
        dfs2(A, 0, sum, res, sb);
        if (res.size() > 0) {
            return res.get(0);
        }
        return "impossible";
    }

    private void dfs2(int[] A, int index, int[] sum, List<String> res, StringBuilder sb) {
        // termination condition
        if (res.size() > 0) return;
        if (index == A.length) {
            if (sum[0] == sum[1] && sum[1] == sum[2]) {
                res.add(new String(sb));
            }
            return;
        }
        // recursive cases
        for (int i = 0; i < 3; i++) {
            sb.append(colors[i]);
            sum[i] += A[index];
            dfs2(A, index + 1, sum, res, sb);
            sum[i] -= A[index];
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {

    }
}

