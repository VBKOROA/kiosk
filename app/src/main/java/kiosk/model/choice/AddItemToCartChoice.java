package kiosk.model.choice;

/**
 * 장바구니에 메뉴 항목을 추가할지 여부를 선택하는 선택지
 */
public sealed interface AddItemToCartChoice extends Choice {
    /**
     * 메뉴 항목을 장바구니에 추가하는 선택지
     */
    record Yes() implements AddItemToCartChoice {}
    /**
     * 메뉴 항목을 장바구니에 추가하지 않고 이전 화면으로 돌아가는 선택지
     */
    record No() implements AddItemToCartChoice {}
}
