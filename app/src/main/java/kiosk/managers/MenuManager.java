package kiosk.managers;

import java.util.ArrayList;
import java.util.List;

import kiosk.enums.MenuCategory;
import kiosk.models.MenuItem;

public class MenuManager {
    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuManager() {
        // 기본 생성자
    }

    public MenuManager(List<MenuItem> menuItems) {
        this.menuItems.addAll(menuItems);
    }

    public List<MenuItem> getMenuItems() {
        // 수정 불가능한 리스트를 반환
        return List.copyOf(menuItems);
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public int getMenuSize() {
        return menuItems.size();
    }

    public List<MenuItem> getMenuItemsByCategory(MenuCategory category) {
        return menuItems.stream()
                .filter(item -> item.category().equals(category)) // 카테고리로 필터링
                .toList(); // 리스트로 변환 (불변 리스트)
    }
}
