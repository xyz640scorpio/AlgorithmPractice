import java.util.*;

/*
APIs:
1. Game Initializaiton
2. make Movement
3. isGameEnd
4. whoWin
*/
public class TicTacToeGame {
    private Board board;
    private final Player player1;
    private final Player player2;
    private String winner;

    public TicTacToeGame(String name1, String name2, int board_size) {
        board = new Board(board_size);
        player1 = new Player(name1, 1);
        player2 = new Player(name2, -1);
    }

    // if successfully
    public int makeMove(int i, int j, Player p) {
        if (board.validMove(i, j)) {
            board.makeMove(i, j, p.side);
            if (board.isWin(i, j, p.side)) {
                this.winner = p.name;
            }
        }
        return -1;
    }

    public boolean isGameOver() {
        return this.winner != null || board.isFull();
    }

    public String whoIsWinner() {
        if (this.winner != null) {
            return this.winner;
        }
        return "No Winner!";
    }
}

class Board {
    private final static int DEFAULT_BOARD_SIZE = 3;
    final int size;
    int[][] board;

    public Board(int size) {
        if (size < 3) {
            this.size = DEFAULT_BOARD_SIZE;
        } else {
            this.size = size;
        }
        this.board = new int[this.size][this.size];
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean validMove(int i, int j) {
        return board[i][j] == 0;
    }

    public void makeMove(int i, int j, int side) {
        board[i][j] = side;
    }

    public boolean isWin(int i, int j, int side) {
        boolean row = true, column = true, diagonal = true;
        for (int k = 0; k < this.size; k++) {
            row = board[i][k] == side && row;
            column = board[k][j] == side && column;
            diagonal = board[k][k] == side && diagonal;
        }
        return row || column || diagonal;
    }
}

class Player {
    final String name;
    int side;

    public Player (String name, int side) {
        this.name = name;
        this.side = side;
    }

}