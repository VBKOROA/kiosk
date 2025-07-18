package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.model.KioskAction;
import kiosk.ui.KioskUI;

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
        int choice = kioskUI.cartCheckBeforeOrderUi(cartManager.getCartItemAsList(), cartManager.getTotalPrice());

        if (choice == 1) {
            return new KioskAction.DiscountMenu();
        } else {
            return new KioskAction.MainMenu();
        }
    }
}
