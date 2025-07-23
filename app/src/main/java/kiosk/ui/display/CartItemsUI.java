package kiosk.ui.display;

import java.util.List;
import java.util.Map;

import kiosk.model.MenuItem;
import kiosk.ui.common.Displayable;

/**
 * 장바구니 아이템 목록을 출력하는 UI 클래스
 * 사용자가 장바구니에 담긴 아이템들을 확인할 수 있도록 목록을 표시
 */
public class CartItemsUI implements Displayable {
    private final List<Map.Entry<MenuItem, Integer>> cartItems;

    private CartItemsUI(List<Map.Entry<MenuItem, Integer>> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * CartItemsUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param cartItems 장바구니 아이템 목록
     * @return CartItemsUI 인스턴스
     */
    public static CartItemsUI withParameter(List<Map.Entry<MenuItem, Integer>> cartItems) {
        return new CartItemsUI(cartItems);
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
}
