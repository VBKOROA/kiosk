package kiosk.handler;

import kiosk.category.SaleCategory;
import kiosk.model.action.KioskAction;
import kiosk.model.choice.DiscountMenuChoice;
import kiosk.ui.UIFactory;

/**
 * 할인 메뉴를 처리하는 핸들러 클래스
 * 
 * 할인 카테고리 메뉴를 표시하고, 사용자의 선택에 따라 적절한 액션을 반환함
 */
public class DiscountMenuHandler implements ActionHandler {
    private final UIFactory uiFactory;

    private DiscountMenuHandler(UIFactory uiFactory) {
        this.uiFactory = uiFactory;
    }

    /**
     * {@link DiscountMenuHandler}의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param uiFactory {@link UIFactory} 인스턴스
     * @return {@link DiscountMenuHandler} 인스턴스
     */
    public static DiscountMenuHandler withParameter(UIFactory uiFactory) {
        return new DiscountMenuHandler(uiFactory);
    }

    /**
     * 할인 메뉴를 표시하고 사용자의 선택을 처리한다.
     * 
     * @return {@link KioskAction} 객체
     */
    @Override
    public KioskAction handle() {
        var saleCategories = SaleCategory.values();
        var ui = uiFactory.discountMenuUi(saleCategories);
        DiscountMenuChoice choice = ui.prompt();

        return choice.process(this);
    }
}
