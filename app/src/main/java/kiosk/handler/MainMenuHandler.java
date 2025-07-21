package kiosk.handler;

import kiosk.category.MenuCategory;
import kiosk.manager.CartManager;
import kiosk.model.KioskAction;
import kiosk.model.choice.MainMenuChoice;
import kiosk.ui.KioskUI;

public class MainMenuHandler implements ActionHandler {
    private final KioskUI kioskUI;
    private final CartManager cartManager;

    private MainMenuHandler(KioskUI kioskUI, CartManager cartManager) {
        this.kioskUI = kioskUI;
        this.cartManager = cartManager;
    }

    /**
     * MainMenuHandler의 인스턴스를 생성하는 팩토리 메서드.
     * @param kioskUI
     * @param cartManager
     * @return MainMenuHandler 인스턴스
     */
    public static MainMenuHandler withParameter(KioskUI kioskUI, CartManager cartManager) {
        return new MainMenuHandler(kioskUI, cartManager);
    }

    /**
     * 메인 메뉴를 표시하고 사용자의 선택을 처리한다.
     * 
     * @return KioskAction 객체
     */
    @Override
    public KioskAction handle() {
        var categories = MenuCategory.values();
        boolean canOrder = !cartManager.isEmpty();

        MainMenuChoice choice = kioskUI.mainMenuUi(categories, canOrder);

        return switch(choice) {
            case MainMenuChoice.Exit() -> new KioskAction.ProgramExit();
            case MainMenuChoice.GoToCategory goToCategory -> new KioskAction.MenuSelectMenu(goToCategory.category());
            case MainMenuChoice.Order() -> new KioskAction.CartCheckBeforeOrder();
            case MainMenuChoice.CancelCartItems() -> new KioskAction.CancelItems();
        };
    }
}
