package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.AddItemToCartMenuHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.model.MenuItem;

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
