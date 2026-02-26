package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.model.MenuItem;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;
import kiosk.model.choice.AddItemToCartChoice;
import kiosk.ui.UIFactory;

/**
 * 장바구니에 아이템을 추가하는 메뉴를 처리하는 핸들러 클래스
 * 사용자가 메뉴 아이템을 장바구니에 추가할지 여부를 선택할 수 있도록 UI를 표시하고,
 * 선택에 따라 장바구니에 아이템을 추가하는 역할
 */
public class AddItemToCartMenuHandler implements ActionHandler {
    private final UIFactory uiFactory;
    private final MenuItem item;
    private final CartManager cartManager;

    private AddItemToCartMenuHandler(UIFactory uiFactory, MenuItem item, CartManager cartManager) {
        this.uiFactory = uiFactory;
        this.item = item;
        this.cartManager = cartManager;
    }

    /**
     * AddItemToCartMenuHandler의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return AddItemToCartMenuHandler 인스턴스
     */
    public static AddItemToCartMenuHandler withParameter(ParameterDto parameter) {
        return new AddItemToCartMenuHandler(
                parameter.uiFactory(),
                parameter.item(),
                parameter.cartManager());
    }

    /**
     * 장바구니에 아이템을 추가할 것인지 묻는 메뉴를 표시한다.
     * 
     * @return KioskAction 객체
     */
    @Override
    public KioskAction handle() {
        var ui = uiFactory.addItemToCartUi(item);
        AddItemToCartChoice choice = ui.prompt();
        return toAction(choice);
    }

    private KioskAction toAction(AddItemToCartChoice choice) {
        if (choice instanceof AddItemToCartChoice.Yes) {
            return processYes();
        }
        if (choice instanceof AddItemToCartChoice.No) {
            return processNo();
        }
        throw new IllegalStateException("Unknown add-to-cart choice: " + choice);
    }

    /**
     * 장바구니에 아이템을 추가하고 UI에 알린다.
     */
    private void addToCart() {
        cartManager.addItem(item, 1);
        uiFactory.itemAddedToCartUi(item).display();
    }

    public KioskAction processYes() {
        addToCart();
        return new MainMenuAction();
    }

    public KioskAction processNo() {
        return new MainMenuAction();
    }

    /**
     * AddItemToCartMenuHandler의 파라미터 DTO 클래스.
     * 
     * @param uiFactory   UI 팩토리
     * @param item        선택한 메뉴 아이템
     * @param cartManager 장바구니 매니저
     */
    public static record ParameterDto(
            UIFactory uiFactory,
            MenuItem item,
            CartManager cartManager) {
    }
}
