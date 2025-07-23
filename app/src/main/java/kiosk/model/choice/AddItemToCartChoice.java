package kiosk.model.choice;

import kiosk.handler.AddItemToCartMenuHandler;
import kiosk.model.action.KioskAction;

/**
 * 장바구니에 메뉴 항목을 추가할지 여부를 선택하는 선택지
 */
public sealed interface AddItemToCartChoice extends Choice {
    KioskAction process(AddItemToCartMenuHandler handler);

    /**
     * 메뉴 항목을 장바구니에 추가하는 선택지
     */
    record Yes() implements AddItemToCartChoice {
        @Override
        public KioskAction process(AddItemToCartMenuHandler handler) {
            return handler.processYes();
        }
    }

    /**
     * 메뉴 항목을 장바구니에 추가하지 않고 이전 화면으로 돌아가는 선택지
     */
    record No() implements AddItemToCartChoice {
        @Override
        public KioskAction process(AddItemToCartMenuHandler handler) {
            return handler.processNo();
        }
    }
}
