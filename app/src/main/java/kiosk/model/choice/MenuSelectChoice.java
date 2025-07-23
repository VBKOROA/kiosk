package kiosk.model.choice;

import kiosk.handler.MenuSelectMenuHandler;
import kiosk.model.MenuItem;
import kiosk.model.action.AddItemToCartMenuAction;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;

/**
 * 메뉴 선택 화면에서 선택할 수 있는 선택지를 나타냄
 */
public sealed interface MenuSelectChoice extends Choice {
    KioskAction process(MenuSelectMenuHandler handler);

    /**
     * 이전 화면으로 돌아가는 선택지
     */
    record GoBack() implements MenuSelectChoice {
        @Override
        public KioskAction process(MenuSelectMenuHandler handler) {
            return new MainMenuAction();
        }
    }

    /**
     * 특정 메뉴 항목을 선택하는 선택지
     * 
     * @param item 선택된 메뉴 항목
     */
    record SelectThis(MenuItem item) implements MenuSelectChoice {
        @Override
        public KioskAction process(MenuSelectMenuHandler handler) {
            return new AddItemToCartMenuAction(item);
        }
    }
}
