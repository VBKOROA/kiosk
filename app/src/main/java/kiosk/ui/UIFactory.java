package kiosk.ui;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import kiosk.category.MenuCategory;
import kiosk.category.SaleCategory;
import kiosk.exception.RidiculousException;
import kiosk.model.MenuItem;
import kiosk.ui.choice.AddItemToCartUI;
import kiosk.ui.choice.CancelItemsUI;
import kiosk.ui.choice.CartCheckBeforeOrderUI;
import kiosk.ui.choice.DiscountMenuUI;
import kiosk.ui.choice.MainMenuUI;
import kiosk.ui.choice.MenuSelectUI;
import kiosk.ui.common.Displayable;
import kiosk.ui.display.CompleteOrderUI;
import kiosk.ui.display.ExitUI;
import kiosk.ui.display.ItemAddedToCartUI;
import kiosk.ui.display.RidiculousExceptionUI;

/**
 * 다양한 UI 화면 생성을 담당하는 팩토리 클래스
 * 각종 UI 컴포넌트의 인스턴스를 생성하여 반환
 */
public class UIFactory {
    public Displayable exitUi() {
        return new ExitUI();
    }

    public MainMenuUI mainMenuUi(MenuCategory[] categories, boolean canOrder) throws RidiculousException {
        return MainMenuUI.withParameter(categories, canOrder);
    }

    public MenuSelectUI menuSelectUi(List<MenuItem> items) throws RidiculousException {
        return MenuSelectUI.withParameter(items);
    }

    public AddItemToCartUI addItemToCartUi(MenuItem item) {
        return AddItemToCartUI.withParameter(item);
    }

    public Displayable itemAddedToCartUi(MenuItem item) {
        return ItemAddedToCartUI.withParameter(item);
    }

    public CartCheckBeforeOrderUI cartCheckBeforeOrderUi(List<Map.Entry<MenuItem, Integer>> cartItems,
            BigDecimal totalPrice) {
        return CartCheckBeforeOrderUI.withParameter(cartItems, totalPrice);
    }

    public DiscountMenuUI discountMenuUi(SaleCategory[] saleCategories) {
        return DiscountMenuUI.withParameter(saleCategories);
    }

    public Displayable completeOrderUi(BigDecimal totalPrice) {
        return CompleteOrderUI.withParameter(totalPrice);
    }

    public CancelItemsUI cancelItemsUi(List<Map.Entry<MenuItem, Integer>> cartItems) {
        return CancelItemsUI.withParameter(cartItems);
    }

    public Displayable ridiculousExceptionUI() {
        return new RidiculousExceptionUI();
    }
}