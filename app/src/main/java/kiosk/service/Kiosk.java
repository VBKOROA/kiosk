package kiosk.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kiosk.models.MenuItem;

public class Kiosk {
    private final MenuManager menuManager;
    private final Scanner sc;

    public Kiosk(MenuManager menuManager, Scanner sc) {
        this.menuManager = menuManager;
        this.sc = sc;
    }

    public void start() {
        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");

            for (int i = 0; i < menuManager.getMenuSize(); i++) {
                System.out.println(i + 1 + ". " + menuManager.getMenuItemFromIdx(i));
            }

            System.out.println("0. 종료 | 종료");

            int choice;

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                // 잘못 입력된 버퍼를 청소
                sc.next();
                continue;
            }

            if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            try {
                menuManager.getMenuItemFromIdx(choice - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                continue;
            }

            MenuItem selectedItem = menuManager.getMenuItemFromIdx(choice - 1);
            selectedItem.selected();
        }
    }

    private void mainMenu() {
        while(true) {
            System.out.println("[ MAIN MENU ]");
            var categories = menuManager.getMenuCategories();

            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }

            System.out.println("0. 종료 | 종료");
            int choice;

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                sc.next(); // 잘못 입력된 버퍼를 청소
                continue;
            }

            if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            if (choice < 1 || choice > categories.size()) {
                System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
                continue;
            }

            menuSelectMenu(categories.get(choice - 1));
        }
    }

    private void menuSelectMenu(String category) {
    }
}
