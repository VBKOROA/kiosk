package kiosk.model.action;

import kiosk.category.MenuCategory;
import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.handler.MenuSelectMenuHandler;

public final record MenuSelectMenuAction(MenuCategory category) implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        var params = new MenuSelectMenuHandler.ParameterDto(dependencies.menuManager(),
                dependencies.kioskUI(),
                category);
        return MenuSelectMenuHandler.withParameter(params);
    }
}
