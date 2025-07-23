package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.model.action.KioskAction;
import kiosk.model.choice.CartCheckBeforeOrderChoice;
import kiosk.ui.UIFactory;

/**
 * 주문 전 장바구니를 확인하는 핸들러 클래스
 * 사용자의 장바구니 상태를 확인하고, 주문 또는 이전 메뉴로 이동하는 동작을 처리
 */
public class CartCheckBeforeOrderHandler implements ActionHandler {
    private final UIFactory uiFactory;
    private final CartManager cartManager;

    private CartCheckBeforeOrderHandler(UIFactory uiFactory, CartManager cartManager) {
        this.uiFactory = uiFactory;
        this.cartManager = cartManager;
    }

    /**
     * {@link CartCheckBeforeOrderHandler}의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param uiFactory   {@link UIFactory} 인스턴스
     * @param cartManager {@link CartManager} 인스턴스
     * @return {@link CartCheckBeforeOrderHandler} 인스턴스
     */
    public static CartCheckBeforeOrderHandler withParameter(UIFactory uiFactory, CartManager cartManager) {
        return new CartCheckBeforeOrderHandler(uiFactory, cartManager);
    }

    /**
     * 장바구니에 담긴 아이템을 주문하기 전 확인하는 메뉴를 표시한다.
     * 
     * @return {@link KioskAction} 객체
     */
    @Override
    public KioskAction handle() {
        var ui = uiFactory.cartCheckBeforeOrderUi(cartManager.getCartItemAsList(), cartManager.getTotalPrice());
        ui.display();
        CartCheckBeforeOrderChoice choice = (CartCheckBeforeOrderChoice) ui.getChoice();
        return choice.getAction();
    }
}
