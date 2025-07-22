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

public class KioskUI {
    public void exitUi() {
        new ExitUI().display();
    }

    public MainMenuChoice mainMenuUi(MenuCategory[] categories, boolean canOrder) throws RidiculousException {
        var params = new MainMenuUI.ParameterDto(categories, canOrder);
        var ui = MainMenuUI.withParameter(params);
        ui.display();
        return (MainMenuChoice) ui.getChoice();
    }

    public MenuSelectChoice menuSelectUi(List<MenuItem> items) throws RidiculousException {
        var params = new MenuSelectUI.ParameterDto(items);
        var ui = MenuSelectUI.withParameter(params);
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

    public CartCheckBeforeOrderChoice cartCheckBeforeOrderUi(List<Map.Entry<MenuItem, Integer>> cartItems, BigDecimal totalPrice) {
        var params = new CartCheckBeforeOrderUI.ParameterDto(cartItems, totalPrice);
        var ui = CartCheckBeforeOrderUI.withParameter(params);
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
