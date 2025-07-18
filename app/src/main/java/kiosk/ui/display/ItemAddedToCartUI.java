package kiosk.ui.display;

import kiosk.model.MenuItem;
import kiosk.ui.common.Displayable;

public class ItemAddedToCartUI implements Displayable {
    private final MenuItem item;

    private ItemAddedToCartUI(MenuItem item) {
        this.item = item;
    }

    /**
     * ItemAddedToCartUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param item 장바구니에 추가된 메뉴 아이템
     * @return ItemAddedToCartUI 인스턴스
     */
    public static ItemAddedToCartUI withParameter(MenuItem item) {
        return new ItemAddedToCartUI(item);
    }

    /**
     * 장바구니에 아이템이 추가되었음을 알리는 메시지를 출력
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("장바구니에 " + item.name() + "이 추가되었습니다.");
    }
}