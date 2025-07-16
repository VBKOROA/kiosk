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

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }
}
