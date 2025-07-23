package kiosk.handler;

import kiosk.model.action.KioskAction;
import kiosk.model.action.ProgramExitAction;
import kiosk.ui.KioskUI;

/**
 * 프로그램 종료를 처리하는 핸들러 클래스입니다.
 * {@link KioskUI}를 통해 UI 종료를 수행하고, 프로그램을 완전히 종료
 */
public class ProgramExitHandler implements ActionHandler {
    private final KioskUI kioskUI;

    private ProgramExitHandler(KioskUI kioskUI) {
        this.kioskUI = kioskUI;
    }

    /**
     * ProgramExitHandler의 인스턴스를 생성하는 팩토리 메서드.
     * @param kioskUI
     * @return ProgramExitHandler 인스턴스
     */
    public static ProgramExitHandler withParameter(KioskUI kioskUI) {
        return new ProgramExitHandler(kioskUI);
    }

    /**
     * 프로그램을 종료한다.
     */
    @Override
    public KioskAction handle() {
        // 프로그램 종료
        kioskUI.exitUi();
        System.exit(0);
        return new ProgramExitAction();
    }
}
