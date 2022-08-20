import java.util.*;

public class SevenPuzzle {
    static class Board {
        private final static int row = 2, col = 4;
        private final int[][] board = new int[row][col];

        public Board() {}
        public Board(int[] values) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    board[i][j] = values[i * col + j];
                }
            }
        }

        // swap, findZero, hashCode, equals, clone,
        public int[] findZero() {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 0) return new int[]{i, j};
                }
            }
            return null;
        }

        public void swap(int i1, int j1, int i2, int j2) {
            int temp = board[i1][j1];
            board[i1][j1] = board[i2][j2];
            board[i2][j2] = temp;
        }

        public boolean outOfBound(int i, int j) {
            return i < 0 || j < 0 || i >= row || j >= col;
        }

        @Override
        public int hashCode() {
            int code = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    code = code * 10 + board[i][j];
                }
            }
            return code;
        }

        @Override
        public Board clone() {
            Board o = new Board();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    o.board[i][j] = board[i][j];
                }
            }
            return o;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Board)) return false;
            Board b = (Board) o;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (b.board[i][j] != board[i][j]) return false;
                }
            }
            return true;
        }
    }

    private final static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int numOfSteps(int[] values) {
        int step = 0;
        Set<Board> set = new HashSet<>();
        Queue<Board> q = new ArrayDeque<>();
        Board start = new Board(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        Board end = new Board(values);
        set.add(start);
        q.offer(start);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Board board = q.poll();
                if (board.equals(end)) return step;
                int[] pos = board.findZero();
                for (int[] dir: dirs) {
                    if (!board.outOfBound(pos[0] + dir[0], pos[1] + dir[1])) {
                        board.swap(pos[0], pos[1], pos[0] + dir[0], pos[1] + dir[1]);
                        if (!set.contains(board)) {
                            Board newBoard = board.clone();
                            q.offer(newBoard);
                            set.add(newBoard);
                        }
                    }
                }
                step++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] values = {4,1,2,3,5,0,6,7};
        SevenPuzzle o = new SevenPuzzle();
        System.out.println(o.numOfSteps(values));
    }
}
