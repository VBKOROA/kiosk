package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.CancelItemsHandler;
import kiosk.handler.HandlerDependencies;

public final record CancelItemsAction() implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        return CancelItemsHandler.withParameter(dependencies.cartManager(), dependencies.kioskUI());
    }
}
