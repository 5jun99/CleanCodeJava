package cleancode.minesweeper.tobe.cell;

public abstract class Cell {
    protected static final String FLAG_SIGN = "⚑";
    protected static final String UNCHECKED_CELL_SIGN = "□";


    protected boolean isFlagged;
    protected boolean isOpened;

    public abstract String getSign();

    public abstract boolean isLandMine();

    public abstract boolean hasNearByLandMineCount();

    public void flag() {
        isFlagged = true;
    }

    public void open() {
        isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

}
