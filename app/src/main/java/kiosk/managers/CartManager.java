package kiosk.managers;

import java.util.HashMap;
import java.util.Map;

import kiosk.models.MenuItem;

public class CartManager {
    private final Map<MenuItem, Integer> cartItems = new HashMap<>();

    public void addItem(MenuItem item, int quantity){
        cartItems.merge(item, quantity, Integer::sum); // 메서드 참조를 사용
            // (oldValue, newValue) -> oldValue + newValue랑 같은 의미
    }

    public int getTotalPrice() {
        // entrySet 또한 stream이 가능함. 처음 알았음.
        return cartItems.entrySet().stream()
                .mapToInt(entry -> (int) (entry.getKey().price() * entry.getValue()))
                .sum();
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
