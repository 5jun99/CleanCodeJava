package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.level.GameLevel;


public class Minesweeper implements GameInitialization, GameRunning {

    private final GameBoard gameBoard;
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
    private final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();

    public Minesweeper(GameLevel gameLevel) {
       gameBoard = new GameBoard(gameLevel);
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    @Override
    public void run() {
        consoleOutputHandler.showGameStarted();

        while (true) {
            try {
                consoleOutputHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    consoleOutputHandler.printGameWinningComment();
                    break;
                }
                if (doesUserLoseTheGame()) {
                    consoleOutputHandler.printGameLosingComment();
                    break;
                }
                System.out.println();

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (GameException e) {
                consoleOutputHandler.printGameExceptionErrorMessage(e);
            } catch (Exception e) {
                consoleOutputHandler.printSimpleMessage("프로그램에 문제가 생겼습니다.");
//                e.printStackTrace();
            }
        }
    }

    private void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = boardIndexConverter.getSelectedColIndex(cellInput, gameBoard.getColSize());
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

        if (doesUserSelectedToPlantFlag(userActionInput)) {
            gameBoard.flag(selectedRowIndex, selectedColIndex);
            checkIfGameIsOVer();
            return;
        }

        if (doesUserSelectedToOpenCell(userActionInput)) {
            if (gameBoard.isMineInThisCell(selectedRowIndex, selectedColIndex)) {
                gameBoard.open(selectedRowIndex, selectedColIndex);
                changeGameStatusToLose();
                return;
            }

            gameBoard.openNearByCells(selectedRowIndex, selectedColIndex);
            checkIfGameIsOVer();
            return;
        }
        throw new GameException("잘못된 번호를 선택하셨습니다.");
    }

    private void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private static boolean doesUserSelectedToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private static boolean doesUserSelectedToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    private static boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private static boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private void checkIfGameIsOVer() {
        if (gameBoard.isAllChecked()) {
            changeGameStatusToWin();
        }
    }

    private static void changeGameStatusToWin() {
        gameStatus = 1;
    }

    private String getCellInputFromUser() {
        consoleOutputHandler.printCommentForCellInput();
        return consoleInputHandler.getUserInput();
    }

    private String getUserActionInputFromUser() {
        consoleOutputHandler.printCommentForUserActionInput();
        return consoleInputHandler.getUserInput();
    }


}
