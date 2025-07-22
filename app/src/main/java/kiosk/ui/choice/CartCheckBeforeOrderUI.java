package kiosk.ui.choice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import kiosk.model.MenuItem;
import kiosk.model.choice.CartCheckBeforeOrderChoice;
import kiosk.ui.display.CartItemsUI;
import kiosk.util.IntScanner;
import kiosk.util.validator.OneOrTwoFilter;

public class CartCheckBeforeOrderUI extends AbstractChoiceable {
    private final List<Map.Entry<MenuItem, Integer>> cartItems;
    private final BigDecimal totalPrice;

    private CartCheckBeforeOrderUI(List<Map.Entry<MenuItem, Integer>> cartItems, BigDecimal totalPrice) {
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
        int index = IntScanner.withFilter(new OneOrTwoFilter());
        switch (index) {
            case 1:
                choice = new CartCheckBeforeOrderChoice.Order();
                break;
            case 2:
            default:
                choice = new CartCheckBeforeOrderChoice.GoBack();
                break;
        }
    }

    /**
     * CartCheckBeforeOrderUI의 파라미터 DTO 클래스.
     * 
     * @param cartItems  장바구니 아이템 목록
     * @param totalPrice 총 주문 금액
     */
    public static record ParameterDto(
            List<Map.Entry<MenuItem, Integer>> cartItems,
            BigDecimal totalPrice) {
    }
}
