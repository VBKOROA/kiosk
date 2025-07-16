package kiosk.managers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import kiosk.models.MenuItem;

public class CartManager {
    private final Map<MenuItem, Integer> cartItems = new HashMap<>();

    public void addItem(MenuItem item, int quantity){
        cartItems.merge(item, quantity, Integer::sum); // 메서드 참조를 사용
            // (oldValue, newValue) -> oldValue + newValue랑 같은 의미
    }

    public BigDecimal getTotalPrice() {
        // entrySet 또한 stream이 가능함. 처음 알았음.
        return cartItems.entrySet().stream()
                .map(entry -> entry.getKey().price().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add); // reduce(초기값 부터 메서드를 통해 누적)
    }

    public Map<MenuItem, Integer> getCartItems() {
        return Map.copyOf(cartItems); // 불변 맵을 반환
    }

    public void clearCart() {
        cartItems.clear();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public void removeItem(MenuItem item) {
        cartItems.remove(item);
    }
}
