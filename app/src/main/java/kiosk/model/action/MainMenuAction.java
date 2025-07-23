package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.handler.MainMenuHandler;

/**
 * 메인 메뉴를 표시하는 액션
 * 이 액션은 {@link MainMenuHandler}를 통해 처리
 */
public record MainMenuAction() implements KioskAction {
    @Override
    public ActionHandler handlerWithDependencies(HandlerDependencies dependencies) {
        return MainMenuHandler.withParameter(dependencies.uiFactory(), dependencies.cartManager());
    }
}
