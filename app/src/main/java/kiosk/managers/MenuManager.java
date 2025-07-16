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

    /**
     * 메뉴 아이템들을 반환한다.
     * @return 수정 불가능한 메뉴 아이템 리스트
     */
    public List<MenuItem> getMenuItems() {
        // 수정 불가능한 리스트를 반환
        return List.copyOf(menuItems);
    }

    /**
     * 카테고리에 해당하는 메뉴 아이템들을 반환한다.
     * @param category
     * @return 수정 불가능한 메뉴 아이템 리스트
     */
    public List<MenuItem> getMenuItemsByCategory(MenuCategory category) {
        return menuItems.stream()
                .filter(item -> item.category().equals(category)) // 카테고리로 필터링
                .toList(); // 리스트로 변환 (불변 리스트)
    }
}
