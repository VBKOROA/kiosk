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
}
