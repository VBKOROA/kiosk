package kiosk.model.action;

import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;
import kiosk.handler.ProgramExitHandler;

/**
 * 프로그램을 종료하는 액션
 * 이 액션은 {@link ProgramExitHandler}를 통해 처리
 */
public record ProgramExitAction() implements KioskAction {
    @Override
    public ActionHandler handler(HandlerDependencies dependencies) {
        return ProgramExitHandler.withParameter(dependencies.kioskUI());
    }
}
