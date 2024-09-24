package cleancode.minesweeper.tobe;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_CELL_SIGN = "□";
    private static final String BLANK_CELL_SIGN = "■";

    private int nearbyLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    private Cell(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static Cell of(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearbyLandMineCount, isLandMine, isFlagged, isOpened);
    }

    public static Cell create() {
        return Cell.of(0, false, false, false);
    }

    public String getSign() {
        if (isOpened) {
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }
            if (nearbyLandMineCount == 0) {
                return BLANK_CELL_SIGN;
            }
            return String.valueOf(nearbyLandMineCount);
        }

        if(isFlagged){
            return FLAG_SIGN;
        }

        return UNCHECKED_CELL_SIGN;
    }

    public void turnOnLandMine() {
        isLandMine = true;
    }

    public void updateNearByMineCount(int count) {
        nearbyLandMineCount = count;
    }

    public void flag() {
        isFlagged = true;
    }

    public void open() {
        isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasNearByLandMineCount() {
        return nearbyLandMineCount > 0;
    }
}
