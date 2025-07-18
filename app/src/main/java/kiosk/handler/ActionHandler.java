package kiosk.handler;

import kiosk.model.KioskAction;

public interface ActionHandler {
    /**
     * 핸들러의 특정 로직을 실행하고, 다음으로 수행할 KioskAction을 반환.
     *
     * @return 다음으로 실행될 KioskAction 객체
     */
    public KioskAction handle();
}
