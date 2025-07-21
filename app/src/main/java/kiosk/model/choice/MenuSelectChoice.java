package kiosk.model.choice;

import kiosk.model.MenuItem;

public sealed interface MenuSelectChoice extends Choice {
    record GoBack() implements MenuSelectChoice {}
    record SelectThis(MenuItem item) implements MenuSelectChoice {}
}
