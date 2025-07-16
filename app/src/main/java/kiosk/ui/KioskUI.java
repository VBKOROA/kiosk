package kiosk.ui;

import java.util.List;
import java.util.Map;
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

    public int mainMenuUi(MenuCategory[] categories, boolean canOrder) {
        System.out.println("[ MAIN MENU ]");

        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i].toString());
        }

        int lastIndex = categories.length;

        if (canOrder) {
            System.out.println();
            System.out.println("[ ORDER MENU ]");
            System.out.println(lastIndex + 1 + ". Orders");
            System.out.println(lastIndex + 2 + ". Cancel");
            lastIndex += 2;
        }

        System.out.println("0. 종료");
        // 람다는 final 혹은 effectively final값만 사용할 수 있음
        final int lastIndexFinal = lastIndex;
        return IntScanner.withFilter(sc, x -> x >= 0 && x <= lastIndexFinal);
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

    public int cartCheckBeforeOrderUi(Map<MenuItem, Integer> cartItems, double totalPrice) {
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        cartItems.forEach((item, quantity) -> {
            System.out.println(item.toString() + " | " + quantity + "개");
        });
        System.out.println("[ Total ]");
        System.out.println("W " + totalPrice);
        System.out.println("1. 주문     2. 돌아가기");
        return IntScanner.withFilter(sc, x -> x == 1 || x == 2);
    }
}
