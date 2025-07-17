package kiosk.handler;

import kiosk.category.MenuCategory;
import kiosk.manager.CartManager;
import kiosk.model.KioskAction;
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

        int choice = kioskUI.mainMenuUi(categories, canOrder);

        if (choice == 0) {
            // 프로그램 종료
            return new KioskAction.ProgramExit();
        }

        if (choice - 1 < categories.length) {
            return new KioskAction.MenuSelectMenu(categories[choice - 1]);
        } else {
            // choice가 categories.length을 넘은 경우
            // categories.length + 1은 주문 메뉴
            // categories.length + 2는 취소 메뉴
            return processOrderDecision(choice == categories.length + 1);
        }
    }

    /**
     * 주문 결정을 처리한다.
     * 
     * @param isCartCheck 주문 결정 여부
     * @return KioskAction 객체
     */
    private KioskAction processOrderDecision(boolean isCartCheck) {
        if (isCartCheck)
            return new KioskAction.CartCheckBeforeOrder();
        else
            return new KioskAction.CancelItems();
    }

}
