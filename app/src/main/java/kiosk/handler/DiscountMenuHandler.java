package kiosk.handler;

import kiosk.category.SaleCategory;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;
import kiosk.model.action.ProcessingOrderAction;
import kiosk.model.choice.DiscountMenuChoice;
import kiosk.ui.KioskUI;

/**
 * 할인 메뉴를 처리하는 핸들러 클래스
 * 
 * 할인 카테고리 메뉴를 표시하고, 사용자의 선택에 따라 적절한 액션을 반환함
 */
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
        DiscountMenuChoice choice = kioskUI.discountMenuUi(saleCategories);

        return switch(choice) {
            case DiscountMenuChoice.GoBack() -> new MainMenuAction();
            case DiscountMenuChoice.DiscountWithThis(SaleCategory category) 
                -> new ProcessingOrderAction(category);
        };
    }
}
