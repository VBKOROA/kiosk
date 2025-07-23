package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.model.action.DiscountMenuAction;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;
import kiosk.model.choice.CartCheckBeforeOrderChoice;
import kiosk.ui.KioskUI;

/**
 * 주문 전 장바구니를 확인하는 핸들러 클래스
 * 사용자의 장바구니 상태를 확인하고, 주문 또는 이전 메뉴로 이동하는 동작을 처리
 */
public class CartCheckBeforeOrderHandler implements ActionHandler {
    private final KioskUI kioskUI;
    private final CartManager cartManager;

    private CartCheckBeforeOrderHandler(KioskUI kioskUI, CartManager cartManager) {
        this.kioskUI = kioskUI;
        this.cartManager = cartManager;
    }

    /**
     * CartCheckBeforeOrderHandler의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param kioskUI
     * @param cartManager
     * @return CartCheckBeforeOrderHandler 인스턴스
     */
    public static CartCheckBeforeOrderHandler withParameter(KioskUI kioskUI, CartManager cartManager) {
        return new CartCheckBeforeOrderHandler(kioskUI, cartManager);
    }

    /**
     * 장바구니에 담긴 아이템을 주문하기 전 확인하는 메뉴를 표시한다.
     * 
     * @return KioskAction 객체
     */
    @Override
    public KioskAction handle() {
        CartCheckBeforeOrderChoice choice = kioskUI.cartCheckBeforeOrderUi(cartManager.getCartItemAsList(),
                cartManager.getTotalPrice());

        return switch (choice) {
            case CartCheckBeforeOrderChoice.Order() -> new DiscountMenuAction();
            case CartCheckBeforeOrderChoice.GoBack() -> new MainMenuAction();
        };
    }
}
