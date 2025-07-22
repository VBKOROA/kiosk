package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.manager.MenuManager;
import kiosk.ui.KioskUI;

public record HandlerDependencies(
        KioskUI kioskUI,
        CartManager cartManager,
        MenuManager menuManager) {
}
