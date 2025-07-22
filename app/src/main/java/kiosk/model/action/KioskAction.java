package kiosk.model.action;
import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;

public sealed interface KioskAction 
    permits MainMenuAction, ProgramExitAction, CancelItemsAction,
        MenuSelectMenuAction, AddItemToCartMenuAction, CartCheckBeforeOrderAction,
        DiscountMenuAction, ProcessingOrderAction {

    ActionHandler handler(HandlerDependencies dependencies);
}