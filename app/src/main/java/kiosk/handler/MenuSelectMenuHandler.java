package kiosk.handler;

import kiosk.category.MenuCategory;
import kiosk.exception.RidiculousException;
import kiosk.manager.MenuManager;
import kiosk.model.action.KioskAction;
import kiosk.model.choice.MenuSelectChoice;
import kiosk.ui.UIFactory;

/**
 * 메뉴 선택 메뉴를 처리하는 핸들러 클래스
 * 사용자가 특정 카테고리의 메뉴를 선택하고 장바구니에 추가할 수 있도록 함
 */
public class MenuSelectMenuHandler implements ActionHandler {
    private final MenuManager menuManager;
    private final UIFactory uiFactory;
    private final MenuCategory category;

    private MenuSelectMenuHandler(MenuManager menuManager, UIFactory uiFactory, MenuCategory category) {
        this.menuManager = menuManager;
        this.uiFactory = uiFactory;
        this.category = category;
    }

    /**
     * {@link MenuSelectMenuHandler}의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter {@link ParameterDto} 인스턴스
     * @return {@link MenuSelectMenuHandler} 인스턴스
     */
    public static MenuSelectMenuHandler withParameter(ParameterDto parameter) {
        return new MenuSelectMenuHandler(
                parameter.menuManager(),
                parameter.uiFactory(),
                parameter.category());
    }

    /**
     * 카테고리에 해당하는 메뉴 아이템을 선택하고 장바구니에 추가하는 메뉴를 표시한다.
     * 
     * @return {@link KioskAction} 객체
     */
    @Override
    public KioskAction handle() {
        var items = menuManager.getMenuItemsByCategory(category);
        MenuSelectChoice choice = null;
        var ui = uiFactory.menuSelectUi(items);

        try {
            choice = ui.prompt();
        } catch (RidiculousException e) {
            uiFactory.ridiculousExceptionUI().display();
        }

        // choice가 null일 경우 RidiculousExceptionUI에 의해 프로그램이 종료되므로
        // NullPointerException이 발생하지 않음
        return choice.process(this);
    }

    /**
     * {@link MenuSelectMenuHandler}의 파라미터 DTO 클래스.
     * 
     * @param menuManager {@link MenuManager} 메뉴 매니저
     * @param uiFactory   {@link UIFactory} UI 팩토리
     * @param category    {@link MenuCategory} 선택한 카테고리
     */
    public static record ParameterDto(
            MenuManager menuManager,
            UIFactory uiFactory,
            MenuCategory category) {
    }
}
