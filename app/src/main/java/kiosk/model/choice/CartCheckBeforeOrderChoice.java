package kiosk.model.choice;

import kiosk.handler.CartCheckBeforeOrderHandler;
import kiosk.model.action.DiscountMenuAction;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;

/**
 * 주문 전 장바구니를 확인한 후의 선택지를 나타냄
 */
public sealed interface CartCheckBeforeOrderChoice extends Choice {
    KioskAction process(CartCheckBeforeOrderHandler handler);

    /**
     * 주문을 진행하는 선택지
     */
    record Order() implements CartCheckBeforeOrderChoice {
        @Override
        public KioskAction process(CartCheckBeforeOrderHandler handler) {
            return new DiscountMenuAction();
        }
    }

    /**
     * 이전 화면으로 돌아가는 선택지
     */
    record GoBack() implements CartCheckBeforeOrderChoice {
        @Override
        public KioskAction process(CartCheckBeforeOrderHandler handler) {
            return new MainMenuAction();
        }
    }
}
