package kiosk.ui;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kiosk.enums.MenuCategory;
import kiosk.enums.SaleCategory;
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
            System.out.println((i + 1) + ". " + categories[i]);
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
        System.out.println("선택한 메뉴: " + item);
        System.out.println("장바구니에 추가하시겠습니까? (1: 예, 0: 아니오)");
        return IntScanner.withFilter(sc, filter);
    }

    public void itemAddedToCartUi(MenuItem item) {
        System.out.println("장바구니에 " + item.name() + "이 추가되었습니다.");
    }

    public int cartCheckBeforeOrderUi(Map<MenuItem, Integer> cartItems, BigDecimal totalPrice) {
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        displayCartItems(cartItems);
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + totalPrice);
        System.out.println();
        System.out.println("1. 주문     2. 돌아가기");
        return IntScanner.withFilter(sc, x -> x == 1 || x == 2);
    }

    private void displayCartItems(Map<MenuItem, Integer> cartItems) {
        cartItems.forEach((item, quantity) -> {
            System.out.println(item + " | " + quantity + "개");
        });
    }

    public int discountMenuUi(SaleCategory[] saleCategories) {
        System.out.println("할인 정보를 입력해주세요.");
        for (int i = 0; i < saleCategories.length; i++) {
            System.out.println((i + 1) + ". " + saleCategories[i]);
        }
        System.out.println("0. 돌아가기");
        return IntScanner.withFilter(sc, x -> x >= 0 && x <= saleCategories.length);
    }

    public void completeOrderUi(BigDecimal totalPrice) {
        System.out.println("주문이 완료되었습니다.");
        System.out.println("총 결제 금액: W " + totalPrice);
        sc.nextLine();
    }
}
