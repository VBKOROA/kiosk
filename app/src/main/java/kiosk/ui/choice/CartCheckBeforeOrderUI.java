package kiosk.ui.choice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kiosk.model.MenuItem;
import kiosk.ui.display.CartItemsUI;
import kiosk.util.IntScanner;

public class CartCheckBeforeOrderUI extends AbstractChoiceable {
    private final List<Map.Entry<MenuItem, Integer>> cartItems;
    private final BigDecimal totalPrice;

    private CartCheckBeforeOrderUI(Scanner sc, List<Map.Entry<MenuItem, Integer>> cartItems, BigDecimal totalPrice) {
        super(sc);
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    /**
     * CartCheckBeforeOrderUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return CartCheckBeforeOrderUI 인스턴스
     */
    public static CartCheckBeforeOrderUI withParameter(ParameterDto parameter) {
        return new CartCheckBeforeOrderUI(
                parameter.sc(),
                parameter.cartItems(),
                parameter.totalPrice());
    }

    /**
     * 주문 전 장바구니 내역을 확인하고 사용자의 선택을 처리한다.
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        CartItemsUI
            .withParameter(cartItems)
            .display();
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + totalPrice);
        System.out.println();
        System.out.println("1. 주문     2. 돌아가기");
        choice = IntScanner.withFilter(sc, x -> x == 1 || x == 2);
    }

    /**
     * CartCheckBeforeOrderUI의 파라미터 DTO 클래스.
     * 
     * @param sc
     * @param cartItems  장바구니 아이템 목록
     * @param totalPrice 총 주문 금액
     */
    public static record ParameterDto(
            Scanner sc,
            List<Map.Entry<MenuItem, Integer>> cartItems,
            BigDecimal totalPrice) {
    }
}
