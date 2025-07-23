package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.manager.MenuManager;
import kiosk.ui.UIFactory;

public record HandlerDependencies(
        UIFactory uiFactory,
        CartManager cartManager,
        MenuManager menuManager) {
}
