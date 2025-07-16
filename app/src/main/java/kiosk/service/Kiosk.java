package kiosk.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import kiosk.enums.MenuCategory;
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

            int choice  = intScanner(sc);

            if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                return;
            }

            if (choice < 1 || choice > categories.length) {
                System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
                continue;
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

            int choice = intScanner(sc);

            if (choice == 0) {
                System.out.println("메뉴 선택을 종료합니다.");
                return;
            }

            if (choice < 1 || choice > items.size()) {
                System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
                continue;
            }

            MenuItem selectedItem = items.get(choice - 1);
            selectedItem.selected();
        }
    }

    private int intScanner(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            sc.next(); // 잘못 입력된 버퍼를 청소
            return intScanner(sc); // 재귀 호출
        }
    }
}
