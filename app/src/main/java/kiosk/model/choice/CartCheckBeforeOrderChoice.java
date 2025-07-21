package kiosk.model.choice;

public sealed interface CartCheckBeforeOrderChoice extends Choice {
    record Order() implements CartCheckBeforeOrderChoice {}
    record GoBack() implements CartCheckBeforeOrderChoice {}
}
