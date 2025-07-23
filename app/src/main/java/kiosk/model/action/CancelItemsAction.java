package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.CancelItemsHandler;
import kiosk.handler.HandlerDependencies;

/**
 * 장바구니의 모든 항목을 취소하는 액션
 * 이 액션은 {@link CancelItemsHandler}를 통해 처리
 */
public final record CancelItemsAction() implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        return CancelItemsHandler.withParameter(dependencies.cartManager(), dependencies.kioskUI());
    }
}
