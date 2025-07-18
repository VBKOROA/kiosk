package kiosk.service;

import kiosk.handler.HandlerFactory;
import kiosk.model.KioskAction;

public class Kiosk {
    private final HandlerFactory handlerFactory;

    public Kiosk(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    public void run() {
        KioskAction curAction = new KioskAction.MainMenu();
        while (true) {
            curAction = handlerFactory.createHandler(curAction).handle();
        }
    }
}
