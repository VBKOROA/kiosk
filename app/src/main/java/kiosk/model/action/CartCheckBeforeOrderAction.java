package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.CartCheckBeforeOrderHandler;
import kiosk.handler.HandlerDependencies;

/**
 * 주문 전 장바구니를 확인하는 액션
 * 이 액션은 {@link CartCheckBeforeOrderHandler}를 통해 처리
 */
public final record CartCheckBeforeOrderAction() implements KioskAction {
    @Override
    public ActionHandler handlerWithDependencies(HandlerDependencies dependencies) {
        return CartCheckBeforeOrderHandler.withParameter(dependencies.uiFactory(), dependencies.cartManager());
    }
}
