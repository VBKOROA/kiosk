package kiosk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return Collections.unmodifiableList(menuItems);
    }

    public MenuItem getMenuItemFromIdx(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx >= menuItems.size()) {
            throw new IndexOutOfBoundsException("잘못된 선택입니다.");
        }
        return menuItems.get(idx);
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public int getMenuSize() {
        return menuItems.size();
    }

    public List<String> getMenuCategories() {
        return menuItems.stream()
                .map(MenuItem::category) // 카테고리 추출 후
                .distinct() // 중복을 제거해서
                .toList(); // 리스트로 변환 (불변 리스트)
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuItems.stream()
                .filter(item -> item.category().equalsIgnoreCase(category)) // 카테고리로 필터링
                .toList(); // 리스트로 변환 (불변 리스트)
    }
}
