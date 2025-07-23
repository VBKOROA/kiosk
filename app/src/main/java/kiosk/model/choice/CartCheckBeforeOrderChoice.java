package kiosk.model.choice;

/**
 * 주문 전 장바구니를 확인한 후의 선택지를 나타냄
 */
public sealed interface CartCheckBeforeOrderChoice extends Choice {
    /**
     * 주문을 진행하는 선택지
     */
    record Order() implements CartCheckBeforeOrderChoice {}
    /**
     * 이전 화면으로 돌아가는 선택지
     */
    record GoBack() implements CartCheckBeforeOrderChoice {}
}