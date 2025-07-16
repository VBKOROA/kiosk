package kiosk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kiosk.models.MenuItem;

public class Kiosk {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private Scanner sc;

    public void setScanner(Scanner scanner) {
        this.sc = scanner;
    }

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems.addAll(menuItems);
    }
}
