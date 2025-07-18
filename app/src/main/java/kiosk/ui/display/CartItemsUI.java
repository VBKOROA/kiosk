package kiosk.ui.display;

import java.util.List;
import java.util.Map;

import kiosk.model.MenuItem;
import kiosk.ui.common.Displayable;

public class CartItemsUI implements Displayable {
    private final List<Map.Entry<MenuItem, Integer>> cartItems;

    private CartItemsUI(List<Map.Entry<MenuItem, Integer>> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * CartItemsDisplayer의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return CartItemsDisplayer 인스턴스
     */
    public static CartItemsUI withParameter(ParameterDto parameter) {
        return new CartItemsUI(parameter.cartItems());
    }

    /**
     * 장바구니 아이템 목록을 출력한다.
     */
    @Override
    public void display() {
        int idx = 1;
        for (var entry : cartItems) {
            System.out.println(idx++ + ". " + entry.getKey() + " | " + entry.getValue() + "개");
        }
    }

    /**
     * CartItemsDisplayer의 파라미터 DTO 클래스.
     * 
     * @param cartItems 장바구니 아이템 목록
     */
    public static record ParameterDto(
            List<Map.Entry<MenuItem, Integer>> cartItems) {
    }
}
