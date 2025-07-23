package kiosk.model.choice;

import kiosk.category.SaleCategory;

/**
 * 할인 메뉴와 관련된 선택지를 나타냄
 */
public sealed interface DiscountMenuChoice extends Choice {
    /**
     * 이전 화면으로 돌아가는 선택지
     */
    record GoBack() implements DiscountMenuChoice {}
    /**
     * 특정 할인 카테고리를 선택하는 선택지
     * @param category 선택된 할인 카테고리
     */
    record DiscountWithThis(SaleCategory category) implements DiscountMenuChoice {}
}
