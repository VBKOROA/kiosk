package kiosk.model.action;
import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;

public sealed interface KioskAction permits MainMenuAction, ProgramExitAction {
    // record ProgramExit() implements KioskAction {}
    // record CancelItems() implements KioskAction {}
    // record MenuSelectMenu(MenuCategory category) implements KioskAction {}
    // record AddItemToCartMenu(MenuItem item) implements KioskAction {}
    // record CartCheckBeforeOrder() implements KioskAction {}
    // record DiscountMenu() implements KioskAction {}
    // record ProcessingOrder(SaleCategory category) implements KioskAction {}

    ActionHandler handler(HandlerDependencies dependencies);
}