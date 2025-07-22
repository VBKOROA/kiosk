package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.DiscountMenuHandler;
import kiosk.handler.HandlerDependencies;

public final record DiscountMenuAction() implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        return DiscountMenuHandler.withParameter(dependencies.kioskUI());
    }
}
