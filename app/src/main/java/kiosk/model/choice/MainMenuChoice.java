package kiosk.model.choice;

import kiosk.category.MenuCategory;

/**
 * 메인 메뉴에서 선택할 수 있는 다양한 선택지를 나타냄
 */
public sealed interface MainMenuChoice extends Choice {
    /**
     * 프로그램을 종료하는 선택지
     */
    record Exit() implements MainMenuChoice {}
    /**
     * 특정 메뉴 카테고리로 이동하는 선택지
     * @param category 이동할 메뉴 카테고리
     */
    record GoToCategory(MenuCategory category) implements MainMenuChoice {}
    /**
     * 주문을 진행하는 선택지
     */
    record Order() implements MainMenuChoice {}
    /**
     * 장바구니 항목을 취소하는 선택지
     */
    record CancelCartItems() implements MainMenuChoice {}
}
