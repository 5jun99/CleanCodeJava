package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.Cell;
import cleancode.minesweeper.tobe.GameException;

public class ConsoleOutputHandler {
    public void showGameWinningComment() {
        System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");

    }

    public void showGameLosingComment() {
        System.out.println("지뢰를 밟았습니다. GAME OVER!");

    }

    public void showBoard(Cell[][] board) {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < board.length; row++) {
            System.out.printf("%d  ", row + 1);
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col].getSign() + " ");
            }
            System.out.println();
        }
    }

    public void showGameExceptionErrorMessage(GameException e) {
        System.out.println(e.getMessage());
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }
}
