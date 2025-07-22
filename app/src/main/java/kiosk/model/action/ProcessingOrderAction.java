package kiosk.model.action;

import kiosk.category.SaleCategory;
import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.handler.ProcessingOrderHandler;

public final record ProcessingOrderAction(SaleCategory category) implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        var params = new ProcessingOrderHandler.ParameterDto(category,
                dependencies.cartManager(),
                dependencies.kioskUI());
        return ProcessingOrderHandler.withParameter(params);
    }
}
