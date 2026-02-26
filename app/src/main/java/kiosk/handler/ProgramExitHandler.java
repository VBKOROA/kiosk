package kiosk.handler;

import kiosk.model.action.KioskAction;
import kiosk.model.action.ProgramExitAction;
import kiosk.ui.UIFactory;

/**
 * 프로그램 종료를 처리하는 핸들러 클래스
 * {@link UIFactory}를 통해 UI 종료를 수행하고, 프로그램을 완전히 종료
 */
public class ProgramExitHandler implements ActionHandler {
    private final UIFactory uiFactory;

    private ProgramExitHandler(UIFactory uiFactory) {
        this.uiFactory = uiFactory;
    }

    /**
     * {@link ProgramExitHandler}의 인스턴스를 생성하는 팩토리 메서드.
     * @param uiFactory {@link UIFactory} 인스턴스
     * @return {@link ProgramExitHandler} 인스턴스
     */
    public static ProgramExitHandler withParameter(UIFactory uiFactory) {
        return new ProgramExitHandler(uiFactory);
    }

    /**
     * 프로그램을 종료한다.
     */
    @Override
    public KioskAction handle() {
        // 프로그램 종료
        uiFactory.exitUi().display();
        return new ProgramExitAction();
    }
}
