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
     * 키오스크 애플리케이션의 메인 루프
     * 
     * 이 메서드는 현재 액션을 메인 메뉴 액션으로 초기화한 후,
     * 무한 루프에 진입하여 사용자 입력이나 시스템 상태에 따라
     * 다음 액션을 계속 처리함. 
     * 각 액션은 주어진 dependencies와 함께 처리
     */
    public void run() {
        KioskAction curAction = new MainMenuAction()
            .handlerWithDependencies(dependencies)
            .handle();
        while (true) {
            curAction = curAction.handlerWithDependencies(dependencies).handle();
        }
    }
}
