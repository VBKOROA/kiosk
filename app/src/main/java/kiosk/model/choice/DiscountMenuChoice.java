package kiosk.model.choice;

import kiosk.category.SaleCategory;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;
import kiosk.model.action.ProcessingOrderAction;

/**
 * 할인 메뉴와 관련된 선택지를 나타냄
 */
public sealed interface DiscountMenuChoice extends ActionChoice {
    /**
     * 이전 화면으로 돌아가는 선택지
     */
    record GoBack() implements DiscountMenuChoice {
        @Override
        public KioskAction getAction() {
            // 이전 메뉴로 돌아가는 액션을 반환
            return new MainMenuAction();
        }
    }
    /**
     * 특정 할인 카테고리를 선택하는 선택지
     * @param category 선택된 할인 카테고리
     */
    record DiscountWithThis(SaleCategory category) implements DiscountMenuChoice {
        @Override
        public KioskAction getAction() {
            // 선택된 할인 카테고리로 주문을 처리하는 액션을 반환
            return new ProcessingOrderAction(category);
        }}
}
