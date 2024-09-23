package cleancode.minesweeper.tobe;

public class Cell {
    private final String sign;
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String CLOSED_CELL_SIGN = "□";
    private static final String OPENED_CELL_SIGN = "■";
    private Cell(String sign) {
        this.sign = sign;
    }

    public static Cell of(String sign) {
        return new Cell(sign);
    }

    public static Cell ofFlag() {
        return Cell.of(FLAG_SIGN);
    }

    public static Cell ofLandMine() {
        return Cell.of(LAND_MINE_SIGN);
    }

    public static Cell ofClosedCell() {
        return Cell.of(CLOSED_CELL_SIGN);
    }

    public static Cell ofOpenedCell() {
        return Cell.of(OPENED_CELL_SIGN);
    }

    public boolean equalsSign(String sign) {
        return sign.equals(this.sign);
    }

    public boolean isNotClosed() {
        return !isClosed();
    }

    public String getSign() {
        return sign;
    }

    public boolean isClosed() {
        return CLOSED_CELL_SIGN.equals(sign);
    }
}
