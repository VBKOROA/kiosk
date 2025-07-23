package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.AddItemToCartMenuHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.model.MenuItem;

/**
 * 장바구니에 메뉴 항목을 추가하는 액션
 * 이 액션은 {@link AddItemToCartMenuHandler}를 통해 처리
 */
public final record AddItemToCartMenuAction(MenuItem item) implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        var params = new AddItemToCartMenuHandler.ParameterDto(
                dependencies.kioskUI(),
                item,
                dependencies.cartManager());
        return AddItemToCartMenuHandler.withParameter(params);
    }
}
