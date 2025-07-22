package kiosk.model.action;
import kiosk.handler.ActionHandler;
import kiosk.handler.HandlerDependencies;

/**
 * 키오스크에서 수행할 수 있는 다양한 액션을 나타내는 인터페이스
 * 각 액션은 {@link ActionHandler}를 반환하며, 이를 통해 실제 동작을 처리함
 */
public sealed interface KioskAction 
    permits MainMenuAction, ProgramExitAction, CancelItemsAction,
        MenuSelectMenuAction, AddItemToCartMenuAction, CartCheckBeforeOrderAction,
        DiscountMenuAction, ProcessingOrderAction {

    ActionHandler handler(HandlerDependencies dependencies);
}