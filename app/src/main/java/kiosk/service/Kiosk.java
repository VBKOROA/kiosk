package kiosk.service;

import kiosk.handler.HandlerDependencies;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;

public class Kiosk {
    private final HandlerDependencies dependencies;

    public Kiosk(HandlerDependencies dependencies) {
        this.dependencies = dependencies;
    }

    /**
     * 키오스크의 메인 실행 루프를 시작.
     * 현재 상태(KioskAction)를 핸들러 팩토리에 전달하여 다음 핸들러를 가져오고,
     * 이를 실행하는 과정을 프로그램이 종료될 때까지 반복.
     */
    public void run() {
        KioskAction curAction = new MainMenuAction()
            .handler(dependencies)
            .handle();
        while (true) {
            curAction = curAction.handler(dependencies).handle();
        }
    }
}
