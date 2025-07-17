package kiosk.service;

import kiosk.handler.AddItemToCartMenuHandler;
import kiosk.handler.CancelItemsHandler;
import kiosk.handler.CartCheckBeforeOrderHandler;
import kiosk.handler.DiscountMenuHandler;
import kiosk.handler.MainMenuHandler;
import kiosk.handler.MenuSelectMenuHandler;
import kiosk.handler.ProcessingOrderHandler;
import kiosk.handler.ProgramExitHandler;
import kiosk.manager.CartManager;
import kiosk.manager.MenuManager;
import kiosk.model.KioskAction;
import kiosk.ui.KioskUI;

public class Kiosk {
    private final MenuManager menuManager;
    private final KioskUI kioskUI;
    private final CartManager cartManager;

    public Kiosk(MenuManager menuManager, KioskUI kioskUI, CartManager cartManager) {
        this.menuManager = menuManager;
        this.kioskUI = kioskUI;
        this.cartManager = cartManager;
    }

    public void run() {
        KioskAction curAction = new KioskAction.MainMenu();
        while (true) {
            // switch 문을 사용하는 또 다른 방법
            // sealed interface를 사용하여 각 액션을 처리
            // 각 액션에 대한 처리를 switch 문으로 분기
            curAction = switch (curAction) {
                case KioskAction.MainMenu mainMenu ->
                    // MainMenuHandler를 사용하여 메인 메뉴를 처리
                    MainMenuHandler.withParameter(kioskUI, cartManager)
                            .handle();

                case KioskAction.ProgramExit programExit -> {
                    // ProgramExitHandler를 사용하여 프로그램 종료를 처리
                    yield ProgramExitHandler.withParameter(kioskUI)
                            .handle();
                }

                case KioskAction.CancelItems cancelItems ->
                    // CancelItemsHandler를 사용하여 장바구니 아이템 취소를 처리
                    CancelItemsHandler.withParameter(cartManager, kioskUI)
                            .handle();

                case KioskAction.MenuSelectMenu menuSelectMenu -> {
                    // MenuSelectMenuHandler를 사용하여 메뉴 선택을 처리
                    // ParameterDto를 생성하여 필요한 매개변수를 전달
                    var parameter = new MenuSelectMenuHandler.ParameterDto(
                            menuManager,
                            kioskUI,
                            menuSelectMenu.category());
                    yield MenuSelectMenuHandler.withParameter(parameter)
                            .handle();
                }

                case KioskAction.AddItemToCartMenu addItemToCartMenu -> {
                    // AddItemToCartMenuHandler를 사용하여 아이템을 장바구니에 추가
                    // ParameterDto를 생성하여 필요한 매개변수를 전달
                    var parameter = new AddItemToCartMenuHandler.ParameterDto(
                            kioskUI,
                            addItemToCartMenu.item(),
                            cartManager);
                    yield AddItemToCartMenuHandler.withParameter(parameter)
                            .handle();
                }

                case KioskAction.CartCheckBeforeOrder cartCheckBeforeOrder ->
                    // CartCheckBeforeOrderHandler를 사용하여 주문 전 장바구니 확인
                    CartCheckBeforeOrderHandler.withParameter(kioskUI, cartManager)
                            .handle();

                case KioskAction.DiscountMenu discountMenu ->
                    // DiscountMenuHandler를 사용하여 할인 선택 메뉴를 처리
                    DiscountMenuHandler.withParameter(kioskUI)
                            .handle();

                case KioskAction.ProcessingOrder processingOrder -> {
                    // ProcessingOrderHandler를 사용하여 주문 처리
                    // ParameterDto를 생성하여 필요한 매개변수를 전달
                    var parameter = new ProcessingOrderHandler.ParameterDto(
                            processingOrder.category(),
                            cartManager,
                            kioskUI);
                    yield ProcessingOrderHandler.withParameter(parameter)
                            .handle();
                }
            };
        }
    }
}
