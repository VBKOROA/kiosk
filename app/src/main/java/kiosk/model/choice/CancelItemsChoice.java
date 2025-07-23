package kiosk.model.choice;

import kiosk.model.MenuItem;

/**
 * 장바구니 항목을 취소하는 다양한 선택지를 나타냄
 */
public sealed interface CancelItemsChoice extends Choice {
    /**
     * 장바구니의 모든 항목을 취소하는 선택지
     */
    record CancelAll() implements CancelItemsChoice {}
    /**
     * 이전 화면으로 돌아가는 선택지
     */
    record GoBack() implements CancelItemsChoice {}
    /**
     * 특정 메뉴 항목을 취소하는 선택지
     * @param item 취소할 메뉴 항목
     */
    record CancelThis(MenuItem item) implements CancelItemsChoice {}
}
