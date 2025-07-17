package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.model.KioskAction;
import kiosk.model.MenuItem;
import kiosk.ui.KioskUI;

public class CancelItemsHandler implements ActionHandler {
    private final CartManager cartManager;
    private final KioskUI kioskUI;

    private CancelItemsHandler(CartManager cartManager, KioskUI kioskUI) {
        this.cartManager = cartManager;
        this.kioskUI = kioskUI;
    }

    /**
     * CancelItemsHandler의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param cartManager
     * @param kioskUI
     * @return CancelItemsHandler 인스턴스
     */
    public static CancelItemsHandler withParameter(CartManager cartManager, KioskUI kioskUI) {
        return new CancelItemsHandler(cartManager, kioskUI);
    }

    /**
     * 장바구니에 담긴 아이템을 취소하는 메뉴를 표시하고 사용자의 선택을 처리한다.
     * 
     * @return KioskAction 객체
     */
    @Override
    public KioskAction handle() {
        var cartItems = cartManager.getCartItemAsList();
        int choice = kioskUI.cancelItemsUi(cartItems);

        if (choice == 0) {
            return new KioskAction.MainMenu();
        } else if (choice == cartItems.size() + 1) {
            return clearCart();
        } else {
            return removeItemFromCart(cartItems.get(choice - 1).getKey());
        }
    }

    /**
     * 장바구니를 비우고 메인 메뉴로 돌아간다.
     * 
     * @return KioskAction 객체
     */
    private KioskAction clearCart() {
        cartManager.clearCart();
        return new KioskAction.MainMenu();
    }

    /**
     * 장바구니에서 아이템을 제거하고 메인 메뉴 혹은 취소 메뉴로 돌아간다.
     * 
     * @param item 제거할 메뉴 아이템
     * @return KioskAction 객체
     */
    private KioskAction removeItemFromCart(MenuItem item) {
        cartManager.removeItem(item);
        if (cartManager.isEmpty()) {
            return new KioskAction.MainMenu();
        } else {
            return new KioskAction.CancelItems();
        }
    }
}
