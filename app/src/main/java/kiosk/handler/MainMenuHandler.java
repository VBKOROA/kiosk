package kiosk.handler;

import kiosk.category.MenuCategory;
import kiosk.exception.RidiculousException;
import kiosk.manager.CartManager;
import kiosk.model.action.CancelItemsAction;
import kiosk.model.action.CartCheckBeforeOrderAction;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MenuSelectMenuAction;
import kiosk.model.action.ProgramExitAction;
import kiosk.model.choice.MainMenuChoice;
import kiosk.ui.UIFactory;

/**
 * 메인 메뉴에서 사용자의 입력을 처리하는 핸들러 클래스
 * KioskUI와 CartManager를 이용하여 메인 메뉴의 동작을 제어
 */
public class MainMenuHandler implements ActionHandler {
    private final UIFactory uiFactory;
    private final CartManager cartManager;

    private MainMenuHandler(UIFactory uiFactory, CartManager cartManager) {
        this.uiFactory = uiFactory;
        this.cartManager = cartManager;
    }

    /**
     * {@link MainMenuHandler}의 인스턴스를 생성하는 팩토리 메서드.
     * @param uiFactory {@link UIFactory} 인스턴스
     * @param cartManager {@link CartManager} 인스턴스
     * @return {@link MainMenuHandler} 인스턴스
     */
    public static MainMenuHandler withParameter(UIFactory uiFactory, CartManager cartManager) {
        return new MainMenuHandler(uiFactory, cartManager);
    }

    /**
     * 메인 메뉴를 표시하고 사용자의 선택을 처리한다.
     *
     * @return {@link KioskAction} 객체
     */
    @Override
    public KioskAction handle() {
        var categories = MenuCategory.values();
        boolean canOrder = !cartManager.isEmpty();
        var ui = uiFactory.mainMenuUi(categories, canOrder);
        MainMenuChoice choice = null;

        try {
            choice = ui.prompt();
        } catch (RidiculousException e) {
            uiFactory.ridiculousExceptionUI().display();
        }
        
        return switch (choice) {
            case MainMenuChoice.Exit __ -> new ProgramExitAction();
            case MainMenuChoice.GoToCategory c -> new MenuSelectMenuAction(c.category());
            case MainMenuChoice.Order __ -> new CartCheckBeforeOrderAction();
            case MainMenuChoice.CancelCartItems __ -> new CancelItemsAction();
            case null -> throw new IllegalStateException("MainMenuChoice cannot be null here.");
        };
    }
}