package kiosk.service;

import java.math.BigDecimal;

import kiosk.enums.MenuCategory;
import kiosk.enums.SaleCategory;
import kiosk.managers.CartManager;
import kiosk.managers.MenuManager;
import kiosk.models.KioskAction;
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

    public void run() {
        KioskAction curAction = new KioskAction.MainMenu();
        while (true) {
            // switch 문을 사용하는 또 다른 방법
            // sealed interface를 사용하여 각 액션을 처리
            // 각 액션에 대한 처리를 switch 문으로 분기
            curAction = switch (curAction) {
                case KioskAction.MainMenu mainMenu -> 
                    mainMenu();
                case KioskAction.ProgramExit programExit -> {
                    // 프로그램 종료한 후
                    programExit();
                    // 어쨋든 KioskAction을 반환하기
                    yield new KioskAction.ProgramExit();
                }
                case KioskAction.CancelItems cancelItems -> 
                    cancelItems();
                case KioskAction.MenuSelectMenu menuSelectMenu -> 
                    menuSelectMenu(menuSelectMenu.category());
                case KioskAction.AddItemToCartMenu addItemToCartMenu ->
                    addItemToCartMenu(addItemToCartMenu.item());
                case KioskAction.CartCheckBeforeOrder cartCheckBeforeOrder -> 
                    cartCheckBeforeOrder();
                case KioskAction.DiscountMenu discountMenu -> 
                    discountMenu();
                case KioskAction.ProcessingOrder processingOrder ->
                    processingOrder(processingOrder.category());
            };
        }
    }

    /**
     * 메인 메뉴를 표시하고 사용자의 선택을 처리한다.
     * 
     * @return KioskAction 객체
     */
    private KioskAction mainMenu() {
        var categories = MenuCategory.values();
        boolean canOrder = !cartManager.isEmpty();

        int choice = kioskUI.mainMenuUi(categories, canOrder);

        if (choice == 0) {
            // 프로그램 종료
            return new KioskAction.ProgramExit();
        }

        if (choice - 1 < categories.length) {
            return new KioskAction.MenuSelectMenu(categories[choice - 1]);
        } else {
            // choice가 categories.length을 넘은 경우
            // categories.length + 1은 주문 메뉴
            // categories.length + 2는 취소 메뉴
            return processOrderDecision(choice == categories.length + 1);
        }
    }

    /**
     * 주문 결정을 처리한다.
     * 
     * @param isCartCheck 주문 결정 여부
     * @return KioskAction 객체
     */
    private KioskAction processOrderDecision(boolean isCartCheck) {
        if (isCartCheck)
            return new KioskAction.CartCheckBeforeOrder();
        else
            return new KioskAction.CancelItems();
    }

    /**
     * 프로그램을 종료한다.
     */
    private void programExit() {
        // 프로그램 종료
        kioskUI.exitUi();
        System.exit(0);
    }

    /**
     * 장바구니에 담긴 아이템을 취소하는 메뉴를 표시하고 사용자의 선택을 처리한다.
     * 
     * @return KioskAction 객체
     */
    private KioskAction cancelItems() {
        var cartItems = cartManager.getCartItemAsList();
        int choice = kioskUI.cancelItemsUi(cartItems);

        if (choice == 0) {
            return new KioskAction.MainMenu();
        } else if (choice == cartItems.size() + 1) {
            return clearCart();
        } else {
            return removeItemFromCart(cartItems.get(choice - 1).getKey());
        }
    }

    /**
     * 장바구니에서 아이템을 제거하고 메인 메뉴 혹은 취소 메뉴로 돌아간다.
     * 
     * @param item 제거할 메뉴 아이템
     * @return KioskAction 객체
     */
    private KioskAction removeItemFromCart(MenuItem item) {
        cartManager.removeItem(item);
        if (cartManager.isEmpty()) {
            return new KioskAction.MainMenu();
        } else {
            return new KioskAction.CancelItems();
        }
    }

    /**
     * 장바구니를 비우고 메인 메뉴로 돌아간다.
     * 
     * @return KioskAction 객체
     */
    private KioskAction clearCart() {
        cartManager.clearCart();
        return new KioskAction.MainMenu();
    }

    /**
     * 카테고리에 해당하는 메뉴 아이템을 선택하고 장바구니에 추가하는 메뉴를 표시한다.
     * 
     * @param category 선택한 카테고리
     * @return KioskAction 객체
     */
    private KioskAction menuSelectMenu(MenuCategory category) {
        var items = menuManager.getMenuItemsByCategory(category);
        int choice = kioskUI.menuSelectUi(items, x -> x >= 0 && x <= items.size());

        if (choice == 0) {
            return new KioskAction.MainMenu();
        }

        MenuItem selectedItem = items.get(choice - 1);
        return new KioskAction.AddItemToCartMenu(selectedItem);
    }

    /**
     * 장바구니에 아이템을 추가할 것인지 묻는 메뉴를 표시한다.
     * 
     * @param item 선택한 메뉴 아이템
     * @return KioskAction 객체
     */
    private KioskAction addItemToCartMenu(MenuItem item) {
        int choice = kioskUI.addItemToCartUi(item, x -> x == 0 || x == 1);

        if (choice == 1) {
            // 장바구니에 담기
            addToCart(item);
        }

        // choice가 0인 경우와 담기가 끝났을 시
        // 메인 메뉴로 돌아가기
        return new KioskAction.MainMenu();
    }

    /**
     * 장바구니에 아이템을 추가하고 UI에 알린다.
     * 
     * @param item 추가할 메뉴 아이템
     */
    private void addToCart(MenuItem item) {
        cartManager.addItem(item, 1);
        kioskUI.itemAddedToCartUi(item);
    }

    /**
     * 장바구니에 담긴 아이템을 주문하기 전 확인하는 메뉴를 표시한다.
     * 
     * @return KioskAction 객체
     */
    private KioskAction cartCheckBeforeOrder() {
        int choice = kioskUI.cartCheckBeforeOrderUi(cartManager.getCartItemAsList(), cartManager.getTotalPrice());

        if (choice == 1) {
            return new KioskAction.DiscountMenu();
        } else {
            return new KioskAction.MainMenu();
        }
    }

    /**
     * 할인 메뉴를 표시하고 사용자의 선택을 처리한다.
     * 
     * @return KioskAction 객체
     */
    private KioskAction discountMenu() {
        var saleCategories = SaleCategory.values();
        int choice = kioskUI.discountMenuUi(saleCategories);
        if (choice == 0) {
            return new KioskAction.MainMenu();
        } else {
            return new KioskAction.ProcessingOrder(saleCategories[choice - 1]);
        }
    }

    /**
     * 주문을 처리하는 메서드
     * 
     * @param saleCategory 선택한 할인 카테고리
     * @return KioskAction 객체
     */
    private KioskAction processingOrder(SaleCategory saleCategory) {
        BigDecimal totalPrice = cartManager.getTotalPrice();
        BigDecimal finalPrice = totalPrice.subtract(totalPrice.multiply(saleCategory.getDiscountRate()));
        cartManager.clearCart();
        kioskUI.completeOrderUi(finalPrice);
        return new KioskAction.MainMenu();
    }
}
