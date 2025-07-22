package kiosk.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import kiosk.exception.RidiculousException;
import kiosk.manager.CartManager;
import kiosk.manager.MenuManager;
import kiosk.model.action.KioskAction;
import kiosk.ui.KioskUI;

public class HandlerFactory {
    private final KioskUI kioskUI;
    private final CartManager cartManager;
    private final MenuManager menuManager;
    /**
     * handlers 맵은 KioskAction 클래스의 서브클래스와 해당 ActionHandler를 매핑하는 역할
     * 각 KioskAction에 대해 적절한 ActionHandler를 생성하는 팩토리 메서드를 제공
     * 이 맵은 KioskAction의 서브클래스 타입을 키로 하고, 
     * 해당 KioskAction을 처리하는 ActionHandler를 생성하는 함수를 값으로 가짐
     * 이를 통해 KioskAction에 따라 적절한 ActionHandler를 동적으로 생성 가능
     */
    private final Map<Class<? extends KioskAction>, Function<KioskAction, ActionHandler>> handlers = new HashMap<>();

    private HandlerFactory(KioskUI kioskUI, CartManager cartManager, MenuManager menuManager) {
        this.kioskUI = kioskUI;
        this.cartManager = cartManager;
        this.menuManager = menuManager;
        handlerMapInit();
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
     * ActionHandler를 생성하기 전에 handlers 맵을 초기화한다.
     * 이 메서드는 HandlerFactory의 생성자에서 호출되어야 함.
     */
    private void handlerMapInit() {
        handlers.put(KioskAction.MainMenu.class,
                action -> MainMenuHandler.withParameter(kioskUI, cartManager));
                
        handlers.put(KioskAction.ProgramExit.class,
                action -> ProgramExitHandler.withParameter(kioskUI));

        handlers.put(KioskAction.CancelItems.class,
                action -> CancelItemsHandler.withParameter(cartManager, kioskUI));

        handlers.put(KioskAction.MenuSelectMenu.class,
                action -> {
                    var rawAction = (KioskAction.MenuSelectMenu) action;
                    var parameter = new MenuSelectMenuHandler.ParameterDto(
                            menuManager,
                            kioskUI,
                            rawAction.category());
                    return MenuSelectMenuHandler.withParameter(parameter);
                });

        handlers.put(KioskAction.AddItemToCartMenu.class,
                action -> {
                    var rawAction = (KioskAction.AddItemToCartMenu) action;
                    var parameter = new AddItemToCartMenuHandler.ParameterDto(
                            kioskUI,
                            rawAction.item(),
                            cartManager);
                    return AddItemToCartMenuHandler.withParameter(parameter);
                });

        handlers.put(KioskAction.CartCheckBeforeOrder.class,
                action -> CartCheckBeforeOrderHandler.withParameter(kioskUI, cartManager));

        handlers.put(KioskAction.DiscountMenu.class,
                action -> DiscountMenuHandler.withParameter(kioskUI));

        handlers.put(KioskAction.ProcessingOrder.class,
                action -> {
                    var rawAction = (KioskAction.ProcessingOrder) action;
                    var parameter = new ProcessingOrderHandler.ParameterDto(
                            rawAction.category(),
                            cartManager,
                            kioskUI);
                    return ProcessingOrderHandler.withParameter(parameter);
                });
    }

    /**
     * ActionHandler를 생성하는 메서드.
     * 이 메서드는 KioskAction에 따라 적절한 ActionHandler를 반환한다.
     * 
     * @param action {@link KioskAction} 객체
     * @return {@link ActionHandler} 객체
     * @throws RidiculousException 핸들러가 존재하지 않을 경우 발생
     */
    public ActionHandler createHandler(KioskAction action) throws RidiculousException {
        // 핸들러 맵이 초기화 되었다고 가정.
        var ret = handlers.get(action.getClass()).apply(action);
        if (ret == null) {
            throw new RidiculousException();
        }
        return ret;
    }
}
