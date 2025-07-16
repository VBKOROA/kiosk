package kiosk.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import kiosk.enums.MenuCategory;

public class KioskUI {
    private final Scanner sc = new Scanner(System.in);

    public void exitUi() {
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }

    public int mainMenuUi(MenuCategory[] categories) {
        System.out.println("[ MAIN MENU ]");

        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i].toString());
        }

        System.out.println("0. 종료 | 종료");
        return intScanner(sc, x -> x >= 0 && x <= categories.length);
    }

    private int intScanner(Scanner sc, ValidationFilter filter) {
        try {
            int input = sc.nextInt();
            if (filter.validate(input)) {
                return input;
            }
            System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            sc.next(); // 잘못 입력된 버퍼를 청소
        }
        return intScanner(sc, filter); // 재귀 호출
    }

    @FunctionalInterface
    public interface ValidationFilter {
        boolean validate(int input);
    }
}
