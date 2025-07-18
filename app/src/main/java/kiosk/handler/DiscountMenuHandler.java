package kiosk.handler;

import kiosk.category.SaleCategory;
import kiosk.model.KioskAction;
import kiosk.ui.KioskUI;

public class DiscountMenuHandler implements ActionHandler {
    private final KioskUI kioskUI;

    private DiscountMenuHandler(KioskUI kioskUI) {
        this.kioskUI = kioskUI;
    }

    /**
     * DiscountMenuHandler의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param kioskUI
     * @return DiscountMenuHandler 인스턴스
     */
    public static DiscountMenuHandler withParameter(KioskUI kioskUI) {
        return new DiscountMenuHandler(kioskUI);
    }

    /**
     * 할인 메뉴를 표시하고 사용자의 선택을 처리한다.
     * 
     * @return KioskAction 객체
     */
    @Override
    public KioskAction handle() {
        var saleCategories = SaleCategory.values();
        int choice = kioskUI.discountMenuUi(saleCategories);
        if (choice == 0) {
            return new KioskAction.MainMenu();
        } else {
            return new KioskAction.ProcessingOrder(saleCategories[choice - 1]);
        }
    }
}
