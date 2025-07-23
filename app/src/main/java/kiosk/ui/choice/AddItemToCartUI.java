package kiosk.ui.choice;

import kiosk.model.MenuItem;
import kiosk.model.choice.AddItemToCartChoice;
import kiosk.util.IntScanner;
import kiosk.util.validator.OneOrTwoFilter;

/**
 * 사용자가 메뉴 아이템을 장바구니에 추가할지 선택할 수 있는 UI 클래스
 * 선택한 메뉴 아이템 정보를 표시하고, 장바구니에 추가 여부를 입력받음
 */
public class AddItemToCartUI {
    private final MenuItem item;

    private AddItemToCartUI(MenuItem item) {
        this.item = item;
    }

    /**
     * AddItemToCartUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param item 선택한 메뉴 아이템
     * @return AddItemToCartUI 인스턴스
     */
    public static AddItemToCartUI withParameter(MenuItem item) {
        return new AddItemToCartUI(item);
    }

    /**
     * 선택한 메뉴를 장바구니에 추가할지 확인하는 UI를 표시하고 사용자의 선택을 반환한다.
     */
    public AddItemToCartChoice prompt() {
        final int YES = 1;

        System.out.println();
        System.out.println("선택한 메뉴: " + item);
        System.out.println("장바구니에 추가하시겠습니까? (1: 예, 2: 아니오)");
        int index = IntScanner.withFilter(new OneOrTwoFilter());
        if (index == YES) {
            return new AddItemToCartChoice.Yes();
        } else {
            return new AddItemToCartChoice.No();
        }
    }
}