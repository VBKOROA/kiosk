package kiosk.model.choice;

public sealed interface AddItemToCartChoice extends Choice {
    record Yes() implements AddItemToCartChoice {}
    record No() implements AddItemToCartChoice {}
}
