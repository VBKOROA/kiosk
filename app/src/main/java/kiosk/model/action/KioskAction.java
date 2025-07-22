package kiosk.model.action;
import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;

public sealed interface KioskAction 
    permits MainMenuAction, ProgramExitAction, CancelItemsAction,
        MenuSelectMenuAction, AddItemToCartMenuAction {
    // record CartCheckBeforeOrder() implements KioskAction {}
    // record DiscountMenu() implements KioskAction {}
    // record ProcessingOrder(SaleCategory category) implements KioskAction {}

    ActionHandler handler(HandlerDependencies dependencies);
}