package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.handler.MainMenuHandler;

public record MainMenuAction() implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        return MainMenuHandler.withParameter(dependencies.kioskUI(), dependencies.cartManager());
    }
}
