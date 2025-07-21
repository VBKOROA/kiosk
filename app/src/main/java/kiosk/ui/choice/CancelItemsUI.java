package kiosk.ui.choice;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kiosk.model.MenuItem;
import kiosk.model.choice.CancelItemsChoice;
import kiosk.ui.display.CartItemsUI;
import kiosk.util.IntScanner;
import kiosk.util.validator.ValidationFilter;
import kiosk.util.validator.XToYFilter;

public class CancelItemsUI extends AbstractChoiceable {
    private final List<Map.Entry<MenuItem, Integer>> cartItems;

    private CancelItemsUI(Scanner sc, List<Map.Entry<MenuItem, Integer>> cartItems) {
        super(sc);
        this.cartItems = cartItems;
    }

    /**
     * CancelItemsUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param sc
     * @param cartItems 장바구니 아이템 목록
     * @return CancelItemsUI 인스턴스
     */
    public static CancelItemsUI withParameter(Scanner sc,
            List<Map.Entry<MenuItem, Integer>> cartItems) {
        return new CancelItemsUI(sc, cartItems);
    }

    /**
     * 취소할 아이템 목록을 표시하고 사용자의 선택을 처리한다.
     */
    @Override
    public void display() {
        final int cartItemsStartIndex = 1;
        final int allCancelIndex = cartItems.size() + 1;
        final int lastIndex = allCancelIndex;

        System.out.println();
        System.out.println("[ Cancel ]");
        CartItemsUI
                .withParameter(cartItems)
                .display();
        System.out.println(allCancelIndex + ". 전체 취소");
        System.out.println("0. 돌아가기");
        System.out.println();
        ValidationFilter filter = XToYFilter.range(0, lastIndex);
        int index = IntScanner.withFilter(sc, filter);
        if (index == 0) {
            choice = new CancelItemsChoice.GoBack();
        } else if (index == allCancelIndex) {
            choice = new CancelItemsChoice.CancelAll();
        } else {
            choice = new CancelItemsChoice.CancelThis(cartItems.get(index - cartItemsStartIndex).getKey());
        }
    }
}
