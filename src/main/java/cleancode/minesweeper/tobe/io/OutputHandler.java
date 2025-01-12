package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.GameBoard;
import cleancode.minesweeper.tobe.GameException;

import java.util.List;
import java.util.stream.IntStream;

public interface OutputHandler {
    void showGameStarted();

    void showBoard(GameBoard board);

    String generateJoinedColString(GameBoard board);

    void printGameWinningComment();

    void printGameLosingComment();

    void printGameExceptionErrorMessage(GameException e);

    void printSimpleMessage(String message);

    void printCommentForCellInput();

    void printCommentForUserActionInput();
}
