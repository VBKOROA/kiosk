package kiosk.model.choice;

import kiosk.category.SaleCategory;

public sealed interface DiscountMenuChoice extends Choice {
    record GoBack() implements DiscountMenuChoice {}
    record DiscountWithThis(SaleCategory category) implements DiscountMenuChoice {}
}
