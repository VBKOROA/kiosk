package kiosk.model.choice;

import kiosk.model.MenuItem;

public sealed interface CancelItemsChoice extends Choice {
    record CancelAll() implements CancelItemsChoice {}
    record GoBack() implements CancelItemsChoice {}
    record CancelThis(MenuItem item) implements CancelItemsChoice {}
}
