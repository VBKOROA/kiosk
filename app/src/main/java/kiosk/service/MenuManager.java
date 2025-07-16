package kiosk.service;

import java.util.ArrayList;
import java.util.List;

import kiosk.models.MenuItem;

public class MenuManager {
    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuManager(List<MenuItem> menuItems) {
        this.menuItems.addAll(menuItems);
    }
}
