package kiosk.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import kiosk.enums.MenuCategory;
import kiosk.managers.MenuManager;
import kiosk.models.MenuItem;

public class Kiosk {
    private final MenuManager menuManager;
    private final Scanner sc;

    public Kiosk(MenuManager menuManager, Scanner sc) {
        this.menuManager = menuManager;
        this.sc = sc;
    }

    public void mainMenu() {
        while (true) {
            System.out.println("[ MAIN MENU ]");
            var categories = MenuCategory.values();

            for (int i = 0; i < categories.length; i++) {
                System.out.println((i + 1) + ". " + categories[i].toString());
            }

            System.out.println("0. 종료 | 종료");

            int choice = intScanner(sc, x -> x >= 0 && x <= categories.length);

            if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                return;
            }

            menuSelectMenu(categories[choice - 1]);
        }
    }

    private void menuSelectMenu(MenuCategory category) {
        var items = menuManager.getMenuItemsByCategory(category);
        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");

            for (int i = 0; i < items.size(); i++) {
                System.out.println(i + 1 + ". " + items.get(i));
            }

            System.out.println("0. 종료 | 종료");

            int choice = intScanner(sc, x -> x >= 0 && x <= items.size());

            if (choice == 0) {
                System.out.println("메뉴 선택을 종료합니다.");
                return;
            }

            MenuItem selectedItem = items.get(choice - 1);
            selectedItem.selected();
        }
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
