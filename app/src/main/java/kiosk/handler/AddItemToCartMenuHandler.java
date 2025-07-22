package kiosk.handler;

import kiosk.manager.CartManager;
import kiosk.model.MenuItem;
import kiosk.model.action.KioskAction;
import kiosk.model.choice.AddItemToCartChoice;
import kiosk.ui.KioskUI;

public class AddItemToCartMenuHandler implements ActionHandler {
    private final KioskUI kioskUI;
    private final MenuItem item;
    private final CartManager cartManager;

    private AddItemToCartMenuHandler(KioskUI kioskUI, MenuItem item, CartManager cartManager) {
        this.kioskUI = kioskUI;
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
                parameter.kioskUI(),
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
        AddItemToCartChoice choice = kioskUI.addItemToCartUi(item);

        switch(choice) {
            case AddItemToCartChoice.Yes() -> {
                addToCart();
            }
            case AddItemToCartChoice.No() -> {}
        }

        // 메인 메뉴로 돌아가기
        return new KioskAction.MainMenu();
    }

    /**
     * 장바구니에 아이템을 추가하고 UI에 알린다.
     * 
     * @param item 추가할 메뉴 아이템
     */
    private void addToCart() {
        cartManager.addItem(item, 1);
        kioskUI.itemAddedToCartUi(item);
    }

    /**
     * AddItemToCartMenuHandler의 파라미터 DTO 클래스.
     * 
     * @param kioskUI Kiosk UI
     * @param item 선택한 메뉴 아이템
     * @param cartManager 장바구니 매니저
     */
    public static record ParameterDto(
            KioskUI kioskUI,
            MenuItem item,
            CartManager cartManager) {
    }
}
