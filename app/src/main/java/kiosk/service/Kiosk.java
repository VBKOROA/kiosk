package kiosk.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kiosk.models.MenuItem;

public class Kiosk {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private final Scanner sc;

    public Kiosk(List<MenuItem> menuItems, Scanner sc) {
        this.menuItems.addAll(menuItems);
        this.sc = sc;
    }

    public void start() {
        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");

            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println(i + 1 + ". " + menuItems.get(i));
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

            if (choice < 1 || choice > menuItems.size()) {
                // 잘못된 선택에 대한 처리
                System.out.println("잘못된 선택입니다.");
                continue;
            }

            MenuItem selectedItem = menuItems.get(choice - 1);
            selectedItem.selected();
        }
    }
}
