package kiosk.handler;

import kiosk.category.MenuCategory;
import kiosk.manager.MenuManager;
import kiosk.model.KioskAction;
import kiosk.model.MenuItem;
import kiosk.model.choice.MenuSelectChoice;
import kiosk.ui.KioskUI;

public class MenuSelectMenuHandler implements ActionHandler {
    private final MenuManager menuManager;
    private final KioskUI kioskUI;
    private final MenuCategory category;

    private MenuSelectMenuHandler(MenuManager menuManager, KioskUI kioskUI, MenuCategory category) {
        this.menuManager = menuManager;
        this.kioskUI = kioskUI;
        this.category = category;
    }

    /**
     * MenuSelectMenuHandler의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return MenuSelectMenuHandler 인스턴스
     */
    public static MenuSelectMenuHandler withParameter(ParameterDto parameter) {
        return new MenuSelectMenuHandler(
                parameter.menuManager(),
                parameter.kioskUI(),
                parameter.category());
    }

    /**
     * 카테고리에 해당하는 메뉴 아이템을 선택하고 장바구니에 추가하는 메뉴를 표시한다.
     * 
     * @return KioskAction 객체
     */
    @Override
    public KioskAction handle() {
        var items = menuManager.getMenuItemsByCategory(category);
        MenuSelectChoice choice = kioskUI.menuSelectUi(items);

        return switch(choice) {
            case MenuSelectChoice.GoBack() -> new KioskAction.MainMenu();
            case MenuSelectChoice.SelectThis(MenuItem item) -> new KioskAction.AddItemToCartMenu(item);
        };
    }

    /**
     * MenuSelectMenuHandler의 파라미터 DTO 클래스.
     * 
     * @param menuManager 메뉴 매니저
     * @param kioskUI     Kiosk UI
     * @param category    선택한 카테고리
     */
    public static record ParameterDto(
            MenuManager menuManager,
            KioskUI kioskUI,
            MenuCategory category) {
    }
}
