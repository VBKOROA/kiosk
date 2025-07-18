package kiosk.service;

import kiosk.handler.HandlerFactory;
import kiosk.model.KioskAction;

public class Kiosk {
    private final HandlerFactory handlerFactory;

    public Kiosk(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    /**
     * 키오스크의 메인 실행 루프를 시작.
     * 현재 상태(KioskAction)를 핸들러 팩토리에 전달하여 다음 핸들러를 가져오고,
     * 이를 실행하는 과정을 프로그램이 종료될 때까지 반복.
     */
    public void run() {
        KioskAction curAction = new KioskAction.MainMenu();
        while (true) {
            curAction = handlerFactory.createHandler(curAction).handle();
        }
    }
}
