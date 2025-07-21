package kiosk.model.choice;

import kiosk.category.MenuCategory;

public sealed interface MainMenuChoice extends Choice {
    record Exit() implements MainMenuChoice {}
    record GoToCategory(MenuCategory category) implements MainMenuChoice {}
    record Order() implements MainMenuChoice {}
    record CancelCartItems() implements MainMenuChoice {}
}
