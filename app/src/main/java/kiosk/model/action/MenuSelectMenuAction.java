package kiosk.model.action;

import kiosk.category.MenuCategory;
import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.handler.MenuSelectMenuHandler;

/**
 * 특정 메뉴 카테고리를 선택하는 액션
 * 이 액션은 {@link MenuSelectMenuHandler}를 통해 처리
 */
public final record MenuSelectMenuAction(MenuCategory category) implements KioskAction {
    @Override
    public ActionHandler handlerWithDependencies(HandlerDependencies dependencies) {
        var params = new MenuSelectMenuHandler.ParameterDto(dependencies.menuManager(),
                dependencies.uiFactory(),
                category);
        return MenuSelectMenuHandler.withParameter(params);
    }
}
