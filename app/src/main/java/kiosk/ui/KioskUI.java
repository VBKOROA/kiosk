package kiosk.ui;

import java.util.List;
import java.util.Scanner;

import kiosk.enums.MenuCategory;
import kiosk.models.MenuItem;
import kiosk.utils.IntScanner;

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
        return IntScanner.withFilter(sc, x -> x >= 0 && x <= categories.length);
    }

    public int menuSelectUi(List<MenuItem> items, IntScanner.ValidationFilter filter) {
        System.out.println("[ SHAKESHACK MENU ]");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i));
        }

        System.out.println("0. 종료 | 종료");

        return IntScanner.withFilter(sc, filter);
    }

    public int addItemToCartUi(MenuItem item, IntScanner.ValidationFilter filter) {
        System.out.println("선택한 메뉴: " + item.toString());
        System.out.println("장바구니에 추가하시겠습니까? (1: 예, 0: 아니오)");
        return IntScanner.withFilter(sc, filter);
    }

    public void itemAddedToCartUi(MenuItem item) {
        System.out.println("장바구니에 " + item.name() + "이 추가되었습니다.");
    }
}
