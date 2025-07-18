package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.manager.MenuManager;
import kiosk.model.KioskAction;
import kiosk.ui.KioskUI;

public class HandlerFactory {
    private final KioskUI kioskUI;
    private final CartManager cartManager;
    private final MenuManager menuManager;

    private HandlerFactory(KioskUI kioskUI, CartManager cartManager, MenuManager menuManager) {
        this.kioskUI = kioskUI;
        this.cartManager = cartManager;
        this.menuManager = menuManager;
    }

    /**
     * HandlerFactory의 매개변수를 담는 DTO 클래스.
     * 
     * @param kioskUI     KioskUI 인스턴스
     * @param cartManager CartManager 인스턴스
     * @param menuManager MenuManager 인스턴스
     */
    public static record ParameterDto(
            KioskUI kioskUI,
            CartManager cartManager,
            MenuManager menuManager) {
    }

    /**
     * HandlerFactory의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return HandlerFactory 인스턴스
     */
    public static HandlerFactory withParameter(ParameterDto parameter) {
        return new HandlerFactory(
                parameter.kioskUI(),
                parameter.cartManager(),
                parameter.menuManager());
    }

    /**
     * KioskAction에 따라 적절한 ActionHandler를 생성하여 반환.
     * 애플리케이션의 모든 분기 로직이 이 메서드에 중앙 집중화되어 있음.
     * 
     * @param action 현재 키오스크의 상태를 나타내는 액션 객체
     * @return action에 매핑되는 ActionHandler 인스턴스
     */
    public ActionHandler createHandler(KioskAction action) {
        // switch 문을 사용하는 또 다른 방법
        // sealed interface를 사용하여 각 액션을 처리
        // 각 액션에 대한 처리를 switch 문으로 분기
        return switch (action) {
            case KioskAction.MainMenu mainMenu ->
                // MainMenuHandler를 사용하여 메인 메뉴를 처리
                MainMenuHandler.withParameter(kioskUI, cartManager);

            case KioskAction.ProgramExit programExit ->
                // ProgramExitHandler를 사용하여 프로그램 종료를 처리
                ProgramExitHandler.withParameter(kioskUI);

            case KioskAction.CancelItems cancelItems ->
                // CancelItemsHandler를 사용하여 장바구니 아이템 취소를 처리
                CancelItemsHandler.withParameter(cartManager, kioskUI);

            case KioskAction.MenuSelectMenu menuSelectMenu -> {
                // MenuSelectMenuHandler를 사용하여 메뉴 선택을 처리
                // ParameterDto를 생성하여 필요한 매개변수를 전달
                var parameter = new MenuSelectMenuHandler.ParameterDto(
                        menuManager,
                        kioskUI,
                        menuSelectMenu.category());
                yield MenuSelectMenuHandler.withParameter(parameter);
            }

            case KioskAction.AddItemToCartMenu addItemToCartMenu -> {
                // AddItemToCartMenuHandler를 사용하여 아이템을 장바구니에 추가
                // ParameterDto를 생성하여 필요한 매개변수를 전달
                var parameter = new AddItemToCartMenuHandler.ParameterDto(
                        kioskUI,
                        addItemToCartMenu.item(),
                        cartManager);
                yield AddItemToCartMenuHandler.withParameter(parameter);
            }

            case KioskAction.CartCheckBeforeOrder cartCheckBeforeOrder ->
                // CartCheckBeforeOrderHandler를 사용하여 주문 전 장바구니 확인
                CartCheckBeforeOrderHandler.withParameter(kioskUI, cartManager);

            case KioskAction.DiscountMenu discountMenu ->
                // DiscountMenuHandler를 사용하여 할인 선택 메뉴를 처리
                DiscountMenuHandler.withParameter(kioskUI);

            case KioskAction.ProcessingOrder processingOrder -> {
                // ProcessingOrderHandler를 사용하여 주문 처리
                // ParameterDto를 생성하여 필요한 매개변수를 전달
                var parameter = new ProcessingOrderHandler.ParameterDto(
                        processingOrder.category(),
                        cartManager,
                        kioskUI);
                yield ProcessingOrderHandler.withParameter(parameter);
            }
        };
    }
}
