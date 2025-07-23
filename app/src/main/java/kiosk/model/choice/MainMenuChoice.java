package kiosk.model.choice;

import kiosk.category.MenuCategory;
import kiosk.handler.MainMenuHandler;
import kiosk.model.action.CancelItemsAction;
import kiosk.model.action.CartCheckBeforeOrderAction;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MenuSelectMenuAction;
import kiosk.model.action.ProgramExitAction;

/**
 * 메인 메뉴에서 선택할 수 있는 다양한 선택지를 나타냄
 */
public sealed interface MainMenuChoice extends Choice {
    KioskAction process(MainMenuHandler handler);

    /**
     * 프로그램을 종료하는 선택지
     */
    record Exit() implements MainMenuChoice {
        @Override
        public KioskAction process(MainMenuHandler handler) {
            return new ProgramExitAction();
        }
    }

    /**
     * 특정 메뉴 카테고리로 이동하는 선택지
     * 
     * @param category 이동할 메뉴 카테고리
     */
    record GoToCategory(MenuCategory category) implements MainMenuChoice {
        @Override
        public KioskAction process(MainMenuHandler handler) {
            return new MenuSelectMenuAction(category);
        }
    }

    /**
     * 주문을 진행하는 선택지
     */
    record Order() implements MainMenuChoice {
        @Override
        public KioskAction process(MainMenuHandler handler) {
            return new CartCheckBeforeOrderAction();
        }
    }

    /**
     * 장바구니 항목을 취소하는 선택지
     */
    record CancelCartItems() implements MainMenuChoice {
        @Override
        public KioskAction process(MainMenuHandler handler) {
            return new CancelItemsAction();
        }
    }
}
