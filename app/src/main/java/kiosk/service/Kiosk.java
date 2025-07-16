package kiosk.service;

import java.math.BigDecimal;

import kiosk.enums.MenuCategory;
import kiosk.enums.SaleCategory;
import kiosk.managers.CartManager;
import kiosk.managers.MenuManager;
import kiosk.models.MenuItem;
import kiosk.ui.KioskUI;

public class Kiosk {
    private final MenuManager menuManager;
    private final KioskUI kioskUI;
    private final CartManager cartManager;

    public Kiosk(MenuManager menuManager, KioskUI kioskUI, CartManager cartManager) {
        this.menuManager = menuManager;
        this.kioskUI = kioskUI;
        this.cartManager = cartManager;
    }

    /**
     * 메인 메뉴를 표시하고 사용자의 선택을 처리한다.
     */
    public void mainMenu() {
        var categories = MenuCategory.values();
        boolean canOrder = !cartManager.isEmpty();

        int choice = kioskUI.mainMenuUi(categories, canOrder);

        if (choice == 0) {
            // 프로그램 종료
            kioskUI.exitUi();
            System.exit(0);
        }

        try {
            menuSelectMenu(categories[choice - 1]);
        } catch (IndexOutOfBoundsException e) {
            // OOB 예외가 떴다는 것은
            // 필터 또한 통과를 했다는 것
            // 즉 Order 관련 메뉴를 선택했다는 것
            if(choice == 4) cartCheckBeforeOrder();
            else cancelItems();
        }
    }

    /**
     * 장바구니에 담긴 아이템을 취소하는 메뉴를 표시하고 사용자의 선택을 처리한다.
     */
    private void cancelItems() {
        var cartItems = cartManager.getCartItems();
        int choice = kioskUI.cancelItemsUi(cartItems);

        if (choice == 0) {
            mainMenu();
        } else if (choice == cartItems.size() + 1) {
            cartManager.clearCart();
            mainMenu();
        } else {
            cartManager.removeItem(cartManager.getKeyFromIdx(choice - 1));
            if(cartManager.isEmpty()) {
                mainMenu();
            } else {
                cancelItems();
            }
            
        }
    }

    /**
     * 카테고리에 해당하는 메뉴 아이템을 선택하고 장바구니에 추가하는 메뉴를 표시한다.
     * @param category 선택한 카테고리
     */
    private void menuSelectMenu(MenuCategory category) {
        var items = menuManager.getMenuItemsByCategory(category);
        int choice = kioskUI.menuSelectUi(items,  x -> x >= 0 && x <= items.size());

        if (choice == 0) {
            mainMenu();
        }

        MenuItem selectedItem = items.get(choice - 1);
        addItemToCartMenu(selectedItem);
    }

    /**
     * 장바구니에 아이템을 추가할 것인지 묻는 메뉴를 표시한다.
     * @param item 선택한 메뉴 아이템
     */
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

    /**
     * 장바구니에 담긴 아이템을 취소하기 전 확인하는 메뉴를 표시한다.
     */
    private void cartCheckBeforeOrder() {
        int choice = kioskUI.cartCheckBeforeOrderUi(cartManager.getCartItems(), cartManager.getTotalPrice());

        if(choice == 1) {
            discountMenu();
        } else {
            mainMenu();
        }
    }

    /**
     * 할인 메뉴를 표시하고 사용자의 선택을 처리한다.
     */
    private void discountMenu() {
        var saleCategories = SaleCategory.values();
        int choice = kioskUI.discountMenuUi(saleCategories);
        if(choice == 0) {
            mainMenu();
        } else {
            processingOrder(saleCategories[choice - 1]);
        }
    }

    /**
     * 주문을 처리하는 메서드
     * @param saleCategory 선택한 할인 카테고리
     */
    private void processingOrder(SaleCategory saleCategory) {
        BigDecimal totalPrice = cartManager.getTotalPrice();
        BigDecimal finalPrice = totalPrice.subtract(totalPrice.multiply(saleCategory.getDiscountRate()));
        cartManager.clearCart();
        kioskUI.completeOrderUi(finalPrice);
        mainMenu();
    }
}
