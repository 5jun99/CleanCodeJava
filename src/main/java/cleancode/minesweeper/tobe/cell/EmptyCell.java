package cleancode.minesweeper.tobe.cell;

public class EmptyCell extends Cell {

    private static final String BLANK_CELL_SIGN = "■";

    @Override
    public String getSign() {
        if (isOpened) {
            return BLANK_CELL_SIGN;
        }
        if (isFlagged){
            return FLAG_SIGN;
        }
        return UNCHECKED_CELL_SIGN;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasNearByLandMineCount() {
        return false;
    }
}
