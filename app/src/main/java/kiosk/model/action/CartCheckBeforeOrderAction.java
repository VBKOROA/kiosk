package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.CartCheckBeforeOrderHandler;
import kiosk.handler.HandlerDependencies;

public final record CartCheckBeforeOrderAction() implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        return CartCheckBeforeOrderHandler.withParameter(dependencies.kioskUI(), dependencies.cartManager());
    }
}
