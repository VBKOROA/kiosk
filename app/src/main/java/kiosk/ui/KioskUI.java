package kiosk.ui;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import kiosk.category.MenuCategory;
import kiosk.category.SaleCategory;
import kiosk.exception.RidiculousException;
import kiosk.model.MenuItem;
import kiosk.model.choice.AddItemToCartChoice;
import kiosk.model.choice.CancelItemsChoice;
import kiosk.model.choice.CartCheckBeforeOrderChoice;
import kiosk.model.choice.DiscountMenuChoice;
import kiosk.model.choice.MainMenuChoice;
import kiosk.model.choice.MenuSelectChoice;
import kiosk.ui.choice.AddItemToCartUI;
import kiosk.ui.choice.CancelItemsUI;
import kiosk.ui.choice.CartCheckBeforeOrderUI;
import kiosk.ui.choice.DiscountMenuUI;
import kiosk.ui.choice.MainMenuUI;
import kiosk.ui.choice.MenuSelectUI;
import kiosk.ui.display.CompleteOrderUI;
import kiosk.ui.display.ExitUI;
import kiosk.ui.display.ItemAddedToCartUI;
import kiosk.ui.display.RidiculousExceptionUI;

/**
 * 키오스크 애플리케이션의 다양한 UI 화면을 제어하는 역할
 * 각 메서드는 특정 UI 화면을 표시하고,
 * 사용자 입력을 받아 적절한 선택 객체를 반환하거나
 * 단순히 정보를 표시함
 * 이 클래스는 UI 흐름을 관리하며, 메뉴 선택, 장바구니 관리,
 * 할인 적용, 주문 완료 등 주요 사용자 인터페이스 기능을 제공
 */
public class KioskUI {
    public void exitUi() {
        new ExitUI().display();
    }

    public MainMenuChoice mainMenuUi(MenuCategory[] categories, boolean canOrder) throws RidiculousException {
        var ui = MainMenuUI.withParameter(categories, canOrder);
        ui.display();
        return (MainMenuChoice) ui.getChoice();
    }

    public MenuSelectChoice menuSelectUi(List<MenuItem> items) throws RidiculousException {
        var ui = MenuSelectUI.withParameter(items);
        ui.display();
        return (MenuSelectChoice) ui.getChoice();
    }

    public AddItemToCartChoice addItemToCartUi(MenuItem item) {
        var ui = AddItemToCartUI.withParameter(item);
        ui.display();
        return (AddItemToCartChoice) ui.getChoice();
    }

    public void itemAddedToCartUi(MenuItem item) {
        ItemAddedToCartUI.withParameter(item).display();
    }

    public CartCheckBeforeOrderChoice cartCheckBeforeOrderUi(List<Map.Entry<MenuItem, Integer>> cartItems,
            BigDecimal totalPrice) {
        var ui = CartCheckBeforeOrderUI.withParameter(cartItems, totalPrice);
        ui.display();
        return (CartCheckBeforeOrderChoice) ui.getChoice();
    }

    public DiscountMenuChoice discountMenuUi(SaleCategory[] saleCategories) {
        var ui = DiscountMenuUI.withParameter(saleCategories);
        ui.display();
        return (DiscountMenuChoice) ui.getChoice();
    }

    public void completeOrderUi(BigDecimal totalPrice) {
        CompleteOrderUI.withParameter(totalPrice).display();
    }

    public CancelItemsChoice cancelItemsUi(List<Map.Entry<MenuItem, Integer>> cartItems) {
        var ui = CancelItemsUI.withParameter(cartItems);
        ui.display();
        return (CancelItemsChoice) ui.getChoice();
    }

    public void ridiculousExceptionUI() {
        new RidiculousExceptionUI().display();
    }
}
