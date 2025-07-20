package kiosk.ui;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kiosk.category.MenuCategory;
import kiosk.category.SaleCategory;
import kiosk.model.MenuItem;
import kiosk.ui.choice.AddItemToCartUI;
import kiosk.ui.choice.CancelItemsUI;
import kiosk.ui.choice.CartCheckBeforeOrderUI;
import kiosk.ui.choice.DiscountMenuUI;
import kiosk.ui.choice.MainMenuUI;
import kiosk.ui.choice.MenuSelectUI;
import kiosk.ui.display.CompleteOrderUI;
import kiosk.ui.display.ExitUI;
import kiosk.ui.display.ItemAddedToCartUI;

public class KioskUI {
    private final Scanner sc = new Scanner(System.in);

    public void exitUi() {
        new ExitUI().display();
    }

    public int mainMenuUi(MenuCategory[] categories, boolean canOrder) {
        var params = new MainMenuUI.ParameterDto(sc, categories, canOrder);
        var ui = MainMenuUI.withParameter(params);
        ui.display();
        return ui.getChoice();
    }

    public int menuSelectUi(List<MenuItem> items) {
        var params = new MenuSelectUI.ParameterDto(items, sc);
        var ui = MenuSelectUI.withParameter(params);
        ui.display();
        return ui.getChoice();
    }

    public int addItemToCartUi(MenuItem item) {
        var ui = AddItemToCartUI.withParameter(sc, item);
        ui.display();
        return ui.getChoice();
    }

    public void itemAddedToCartUi(MenuItem item) {
        ItemAddedToCartUI.withParameter(item).display();
    }

    public int cartCheckBeforeOrderUi(List<Map.Entry<MenuItem, Integer>> cartItems, BigDecimal totalPrice) {
        var params = new CartCheckBeforeOrderUI.ParameterDto(sc, cartItems, totalPrice);
        var ui = CartCheckBeforeOrderUI.withParameter(params);
        ui.display();
        return ui.getChoice();
    }

    public int discountMenuUi(SaleCategory[] saleCategories) {
        var ui = DiscountMenuUI.withParameter(sc, saleCategories);
        ui.display();
        return ui.getChoice();
    }

    public void completeOrderUi(BigDecimal totalPrice) {
        CompleteOrderUI.withParameter(sc, totalPrice).display();
    }

    public int cancelItemsUi(List<Map.Entry<MenuItem, Integer>> cartItems) {
        var ui = CancelItemsUI.withParameter(sc, cartItems);
        ui.display();
        return ui.getChoice();
    }
}
