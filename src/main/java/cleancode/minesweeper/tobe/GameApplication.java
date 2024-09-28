package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.level.GameLevel;
import cleancode.minesweeper.tobe.level.Intermediate;

public class GameApplication {
    public static void main(String[] args) {
        GameLevel gameLevel = new Intermediate();
        Minesweeper minesweeper = new Minesweeper(gameLevel);
        minesweeper.run();
    }

}
