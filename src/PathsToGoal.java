import java.util.*;
public class PathsToGoal {

    // n is upper bound of the number line
    // dfs solution
    public int distinctMoves1(String moves, int n, int x, int y) {
        // corner case
        Set<String> res = new HashSet<>();
        StringBuilder subsequence = new StringBuilder();
        dfs(res, subsequence, moves, n, x, y, 0);
        return res.size();
    }
    private void dfs (Set<String> res, StringBuilder subsequence, String moves, int n, int x, int y, int index) {
        // base case
        // cross the bound
        if (x > n || x < 0) {
            return;
        }
        // traverse all moves
        if (index == moves.length()) {
            if (x == y) res.add(new String(subsequence));
            return;
        }
        // recursive cases
        // option 1: add current move to subsequence
        char move = moves.charAt(index);
        subsequence.append(move);
        if (move == 'r') {
            x++;
        } else {
            x--;
        }
        dfs(res, subsequence, moves, n, x, y, index + 1);
        subsequence.deleteCharAt(subsequence.length() - 1);
        if (move == 'r') {
            x--;
        } else {
            x++;
        }
        // option 2: not add current move to subsequence
        dfs(res, subsequence, moves, n, x, y, index + 1);
    }
    // dp solution
    public int distinctMoves2() {
        return 0;
    }
    public static void main(String[] args) {
        PathsToGoal o = new PathsToGoal();
        int res = o.distinctMoves1("rrrlrr", 7, 0, 0);
        System.out.println(res);
    }
}

// Time Complexity: O(2^n * n) -- branch 2, level n, each node O(1) O(branch ^ level) = node number
// Space Complexity: call stack O(n)  -- n is the length String moves, heap O(n ) -- StringBuilder

