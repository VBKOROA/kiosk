package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.DiscountMenuHandler;
import kiosk.handler.HandlerDependencies;

/**
 * 할인 메뉴를 표시하는 액션
 * 이 액션은 {@link DiscountMenuHandler}를 통해 처리
 */
public final record DiscountMenuAction() implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        return DiscountMenuHandler.withParameter(dependencies.uiFactory());
    }
}
