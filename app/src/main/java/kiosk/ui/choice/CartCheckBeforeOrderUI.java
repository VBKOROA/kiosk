package kiosk.ui.choice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import kiosk.model.MenuItem;
import kiosk.model.choice.CartCheckBeforeOrderChoice;
import kiosk.ui.display.CartItemsUI;
import kiosk.util.IntScanner;
import kiosk.util.validator.OneOrTwoFilter;

/**
 * 주문 전 장바구니 내역을 확인하고 주문 또는 이전 단계로 돌아갈 수 있는 UI 클래스
 */
public class CartCheckBeforeOrderUI extends AbstractChoiceable {
    private final List<Map.Entry<MenuItem, Integer>> cartItems;
    private final BigDecimal totalPrice;

    private CartCheckBeforeOrderUI(List<Map.Entry<MenuItem, Integer>> cartItems, BigDecimal totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    /**
     * 지정된 장바구니 항목과 총 금액으로 {@code CartCheckBeforeOrderUI}의 새 인스턴스를 생성
     *
     * @param cartItems  각 {@link MenuItem}과 해당 수량이 포함된 엔트리의 리스트
     * @param totalPrice 장바구니에 담긴 상품의 총 금액
     * @return 주어진 파라미터로 초기화된 {@code CartCheckBeforeOrderUI} 인스턴스
     */
    public static CartCheckBeforeOrderUI withParameter(List<Map.Entry<MenuItem, Integer>> cartItems, BigDecimal totalPrice) {
        return new CartCheckBeforeOrderUI(cartItems, totalPrice);
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
}
