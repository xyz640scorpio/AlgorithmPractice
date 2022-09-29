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
    private Player player1;
    private Player player2;

    public TicTacToeGame(String name1, String name2) {
        board = new Board();
        player1 = new Player(name1, 1);
        player2 = new Player(name2, -1);
    }

    public TicTacToeGame(String name1, String name2, int board_size) {
        board = new Board(board_size);
        player1 = new Player(name1, 1);
        player2 = new Player(name2, -1);

    }
}

class Board {
    private final static int DEFAULT_BOARD_SIZE = 3;
    final int size;
    int[][] board;

    public Board() {
        this.size = DEFAULT_BOARD_SIZE;
        this.board = new int[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
    }

    public Board(int size) {
        this.size = size;
        this.board = new int[size][size];
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
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