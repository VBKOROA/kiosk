package kiosk.ui.choice;

import java.util.Scanner;

import kiosk.model.MenuItem;
import kiosk.util.IntScanner;

public class AddItemToCartUI extends AbstractChoiceable {
    private final MenuItem item;

    private AddItemToCartUI(Scanner sc, MenuItem item) {
        super(sc);
        this.item = item;
    }

    /**
     * AddItemToCartUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return AddItemToCartUI 인스턴스
     */
    public static AddItemToCartUI withParameter(Scanner sc, MenuItem item) {
        return new AddItemToCartUI(sc, item);
    }

    /**
     * 선택한 메뉴를 장바구니에 추가할지 확인하는 UI를 표시하고 사용자의 선택을 처리한다.
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("선택한 메뉴: " + item);
        System.out.println("장바구니에 추가하시겠습니까? (1: 예, 0: 아니오)");
        choice = IntScanner.withFilter(sc, x -> x == 0 || x == 1);
    }
}
