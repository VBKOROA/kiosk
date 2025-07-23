package kiosk.model.choice;

import kiosk.handler.CancelItemsHandler;
import kiosk.model.MenuItem;
import kiosk.model.action.KioskAction;

/**
 * 장바구니 항목을 취소하는 다양한 선택지를 나타냄
 */
public sealed interface CancelItemsChoice extends Choice {
    KioskAction process(CancelItemsHandler handler);

    /**
     * 장바구니의 모든 항목을 취소하는 선택지
     */
    record CancelAll() implements CancelItemsChoice {
        @Override
        public KioskAction process(CancelItemsHandler handler) {
            return handler.processCancelAll();
        }
    }

    /**
     * 이전 화면으로 돌아가는 선택지
     */
    record GoBack() implements CancelItemsChoice {
        @Override
        public KioskAction process(CancelItemsHandler handler) {
            return handler.processGoBack();
        }
    }

    /**
     * 특정 메뉴 항목을 취소하는 선택지
     * 
     * @param item 취소할 메뉴 항목
     */
    record CancelThis(MenuItem item) implements CancelItemsChoice {
        @Override
        public KioskAction process(CancelItemsHandler handler) {
            return handler.processCancelThis(item);
        }
    }
}
