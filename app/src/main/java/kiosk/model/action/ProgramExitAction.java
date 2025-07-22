package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.handler.ProgramExitHandler;

public record ProgramExitAction() implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        return ProgramExitHandler.withParameter(dependencies.kioskUI());
    }
}
