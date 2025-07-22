package kiosk.model.action;

import kiosk.category.MenuCategory;
import kiosk.category.SaleCategory;
import kiosk.model.MenuItem;

public sealed interface KioskAction {
    record MainMenu() implements KioskAction {}
    record ProgramExit() implements KioskAction {}
    record CancelItems() implements KioskAction {}
    record MenuSelectMenu(MenuCategory category) implements KioskAction {}
    record AddItemToCartMenu(MenuItem item) implements KioskAction {}
    record CartCheckBeforeOrder() implements KioskAction {}
    record DiscountMenu() implements KioskAction {}
    record ProcessingOrder(SaleCategory category) implements KioskAction {}
}