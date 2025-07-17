package kiosk.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiosk.models.MenuItem;

public class CartManager {
    private final Map<MenuItem, Integer> cartItems = new LinkedHashMap<>();

    /**
     * 장바구니에 아이템을 추가한다.
     * @param item 추가할 아이템
     * @param quantity 추가할 수량
     */
    public void addItem(MenuItem item, int quantity){
        cartItems.merge(item, quantity, Integer::sum); // 메서드 참조를 사용
            // (oldValue, newValue) -> oldValue + newValue랑 같은 의미
    }

    /**
     * 장바구니에 있는 아이템의 총 가격을 계산한다.
     * @return 총 가격
     */
    public BigDecimal getTotalPrice() {
        // entrySet 또한 stream이 가능함. 처음 알았음.
        return cartItems.entrySet().stream()
                .map(entry -> entry.getKey().price().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add); // reduce(초기값 부터 메서드를 통해 누적)
    }

    /**
     * 장바구니에 있는 아이템과 개수를 리스트로 반환한다.
     * @return 장바구니 아이템 리스트
     */
    public List<Map.Entry<MenuItem, Integer>> getCartItemAsList() {
        return new ArrayList<>(cartItems.entrySet());
    }

    /**
     * 장바구니에 있는 아이템을 반환한다.
     * @param idx 아이템의 인덱스
     * @return 아이템
     */
    public MenuItem getKeyFromIdx(int idx) {
        return (MenuItem) cartItems.keySet().toArray()[idx];
    }

    /**
     * 장바구니를 비운다.
     */
    public void clearCart() {
        cartItems.clear();
    }

    /**
     * 장바구니가 비어있는지 확인한다.
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    /**
     * 장바구니에서 아이템을 제거한다.
     * @param item 제거할 아이템
     */
    public void removeItem(MenuItem item) {
        cartItems.remove(item);
    }
}
