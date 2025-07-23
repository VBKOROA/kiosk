package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.model.MenuItem;
import kiosk.model.action.CancelItemsAction;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;
import kiosk.model.choice.CancelItemsChoice;
import kiosk.ui.UIFactory;

/**
 * 장바구니에 담긴 아이템을 취소하는 기능을 담당하는 핸들러 클래스
 * 사용자의 선택에 따라 장바구니 아이템 전체 또는 일부를 취소하거나, 이전 메뉴로 돌아갈 수 있음
 */
public class CancelItemsHandler implements ActionHandler {
    private final CartManager cartManager;
    private final UIFactory uiFactory;

    private CancelItemsHandler(CartManager cartManager, UIFactory uiFactory) {
        this.cartManager = cartManager;
        this.uiFactory = uiFactory;
    }

    /**
     * CancelItemsHandler의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param cartManager {@link CartManager} 인스턴스
     * @param uiFactory {@link UIFactory} 인스턴스
     * @return {@link CancelItemsHandler} 인스턴스
     */
    public static CancelItemsHandler withParameter(CartManager cartManager, UIFactory uiFactory) {
        return new CancelItemsHandler(cartManager, uiFactory);
    }

    /**
     * 장바구니에 담긴 아이템을 취소하는 메뉴를 표시하고 사용자의 선택을 처리한다.
     * 
     * @return {@link KioskAction} 객체
     */
    @Override
    public KioskAction handle() {
        var cartItems = cartManager.getCartItemAsList();
        var ui = uiFactory.cancelItemsUi(cartItems);
        CancelItemsChoice choice = ui.prompt();
        return choice.process(this);
    }

    public KioskAction processCancelAll() {
        cartManager.clearCart();
        return new MainMenuAction();
    }

    public KioskAction processGoBack() {
        return new MainMenuAction();
    }

    public KioskAction processCancelThis(MenuItem item) {
        cartManager.removeItem(item);
        if (cartManager.isEmpty()) {
            return new MainMenuAction();
        } else {
            return new CancelItemsAction();
        }
    }
}
