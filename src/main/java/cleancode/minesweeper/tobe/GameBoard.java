package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.level.GameLevel;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

    public static final int LAND_MINE_COUNT = 10;

    private final Cell[][] board;
    private final int landMineCount;

    public GameBoard(GameLevel gameLevel){
        board = new Cell[gameLevel.getRowSize()][gameLevel.getColSize()];
        landMineCount = gameLevel.getLandMineCount();
    }
    public int getColSize() {
        return board[0].length;
    }

    public int getRowSize() {
        return board.length;
    }

    public void initializeGame() {
        for (int row = 0; row < getRowSize(); row++) {
            for (int col = 0; col < getColSize(); col++) {
                board[row][col] = Cell.create();
            }
        }

        for (int i = 0; i < LAND_MINE_COUNT; i++) {
            int landMineRow = new Random().nextInt(getRowSize());
            int landMineCol = new Random().nextInt(getColSize());
            Cell cell = findCell(landMineRow, landMineCol);
            cell.turnOnLandMine();
        }

        for (int row = 0; row < getRowSize(); row++) {
            for (int col = 0; col < getColSize(); col++) {
                if (isMineInThisCell(row, col)) {
                    continue;
                }
                int count = getNearByMineCount(row, col);
                Cell cell = findCell(row, col);
                cell.updateNearByMineCount(count);
            }
        }
    }

    private int getNearByMineCount(int row, int col) {
        int count = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && isMineInThisCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isMineInThisCell(row - 1, col)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < getColSize() && isMineInThisCell(row - 1, col + 1)) {
            count++;
        }
        if (col - 1 >= 0 && isMineInThisCell(row, col - 1)) {
            count++;
        }
        if (col + 1 < getColSize() && isMineInThisCell(row, col + 1)) {
            count++;
        }
        if (row + 1 < getRowSize() && col - 1 >= 0 && isMineInThisCell(row + 1, col - 1)) {
            count++;
        }
        if (row + 1 < getRowSize() && isMineInThisCell(row + 1, col)) {
            count++;
        }
        if (row + 1 < getRowSize() && col + 1 < getColSize() && isMineInThisCell(row + 1, col + 1)) {
            count++;
        }
        return count;
    }


    public String getSign(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex, colIndex);
        return cell.getSign();
    }

    public Cell findCell(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }

    public void flag(int selectedRowIndex, int selectedColIndex) {
        Cell cell = findCell(selectedRowIndex, selectedColIndex);
        cell.flag();
    }

    public void open(int selectedRowIndex, int selectedColIndex) {
        Cell cell = findCell(selectedRowIndex, selectedColIndex);
        cell.open();
    }

    public void openNearByCells(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= getRowSize() || colIndex < 0 || colIndex >= getColSize()) {
            return;
        }
        if (isCellOpened(rowIndex, colIndex)) {
            return;
        }
        if (isMineInThisCell(rowIndex, colIndex)) {
            return;
        }

        open(rowIndex, colIndex);

        if (DoesCellHaveNearByLandMineCount(rowIndex, colIndex)) {
            return;
        }

        openNearByCells(rowIndex - 1, colIndex - 1);
        openNearByCells(rowIndex - 1, colIndex);
        openNearByCells(rowIndex - 1, colIndex + 1);
        openNearByCells(rowIndex, colIndex - 1);
        openNearByCells(rowIndex, colIndex + 1);
        openNearByCells(rowIndex + 1, colIndex - 1);
        openNearByCells(rowIndex + 1, colIndex);
        openNearByCells(rowIndex + 1, colIndex + 1);
    }

    public boolean isAllChecked() {
        return Arrays.stream(board).flatMap(Arrays::stream).allMatch(Cell::isChecked);
    }

    boolean isMineInThisCell(int selectedRowIndex, int selectedColIndex) {
        Cell cell = findCell(selectedRowIndex, selectedColIndex);
        return cell.isLandMine();
    }

    private boolean DoesCellHaveNearByLandMineCount(int rowIndex, int colIndex) {
        return findCell(rowIndex, colIndex).hasNearByLandMineCount();
    }

    private boolean isCellOpened(int rowIndex, int colIndex) {
        return findCell(rowIndex, colIndex).isOpened();
    }

}
