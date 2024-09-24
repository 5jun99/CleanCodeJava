package cleancode.minesweeper.tobe.io;

import java.util.Scanner;

public class ConsoleInputHandler {
    public static final Scanner SCANNER = new Scanner(System.in);


    public void showGameWinningComment() {
        System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");

    }

    public void showGameLosingComment() {
        System.out.println("지뢰를 밟았습니다. GAME OVER!");

    }

    public String getCellInputFromUser() {
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");
        return SCANNER.nextLine();
    }

    public String getUserActionInputFromUser() {
        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
        return SCANNER.nextLine();
    }
}
