package kiosk.service;

import java.util.Scanner;

import kiosk.enums.MenuCategory;
import kiosk.managers.CartManager;
import kiosk.managers.MenuManager;
import kiosk.models.MenuItem;
import kiosk.ui.KioskUI;

public class Kiosk {
    private final MenuManager menuManager;
    private final Scanner sc;
    private final KioskUI kioskUI;
    private final CartManager cartManager;

    public Kiosk(MenuManager menuManager, Scanner sc, KioskUI kioskUI, CartManager cartManager) {
        this.menuManager = menuManager;
        this.sc = sc;
        this.kioskUI = kioskUI;
        this.cartManager = cartManager;
    }

    public void mainMenu() {
        var categories = MenuCategory.values();

        int choice = kioskUI.mainMenuUi(categories);

        if (choice == 0) {
            // 프로그램 종료
            kioskUI.exitUi();
        }

        menuSelectMenu(categories[choice - 1]);
    }

    private void menuSelectMenu(MenuCategory category) {
        var items = menuManager.getMenuItemsByCategory(category);
        int choice = kioskUI.menuSelectUi(items,  x -> x >= 0 && x <= items.size());

        if (choice == 0) {
            mainMenu();
        }

        MenuItem selectedItem = items.get(choice - 1);
        addItemToCartMenu(selectedItem);
    }

    private void addItemToCartMenu(MenuItem item) {
        int choice = kioskUI.addItemToCartUi(item, x -> x == 0 || x == 1);

        if (choice == 1) {
            // 장바구니에 담기
            cartManager.addItem(item, 1);
            kioskUI.itemAddedToCartUi(item);
        }

        // choice가 0인 경우와 담기가 끝났을 시
        // 메인 메뉴로 돌아가기
        mainMenu();
    }
}
