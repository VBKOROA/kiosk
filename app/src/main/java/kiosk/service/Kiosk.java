package kiosk.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import kiosk.enums.MenuCategory;
import kiosk.managers.MenuManager;
import kiosk.models.MenuItem;
import kiosk.ui.KioskUI;

public class Kiosk {
    private final MenuManager menuManager;
    private final Scanner sc;
    private final KioskUI kioskUI;

    public Kiosk(MenuManager menuManager, Scanner sc, KioskUI kioskUI) {
        this.menuManager = menuManager;
        this.sc = sc;
        this.kioskUI = kioskUI;
    }

    public void mainMenu() {
        while (true) {
            var categories = MenuCategory.values();

            int choice = kioskUI.mainMenuUi(categories);

            if (choice == 0) {
                // 프로그램 종료
                kioskUI.exitUi();
            }

            menuSelectMenu(categories[choice - 1]);
        }
    }

    private void menuSelectMenu(MenuCategory category) {
        var items = menuManager.getMenuItemsByCategory(category);
        int choice = kioskUI.menuSelectUi(items,  x -> x >= 0 && x <= items.size());

        if (choice == 0) {
            System.out.println("메뉴 선택을 종료합니다.");
            return;
        }

        MenuItem selectedItem = items.get(choice - 1);
        kioskUI.menuSelectedUi(selectedItem);
    }
}
