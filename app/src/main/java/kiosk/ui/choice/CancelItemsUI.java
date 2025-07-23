package kiosk.ui.choice;

import java.util.List;
import java.util.Map;

import kiosk.model.MenuItem;
import kiosk.model.choice.CancelItemsChoice;
import kiosk.ui.display.CartItemsUI;
import kiosk.util.IntScanner;
import kiosk.util.validator.ValidationFilter;
import kiosk.util.validator.XToYFilter;

/**
 * 장바구니에서 아이템을 취소하는 UI를 제공하는 클래스
 * 사용자가 취소할 아이템을 선택하거나 전체 취소, 돌아가기를 선택할 수 있음
 */
public class CancelItemsUI extends AbstractChoiceable {
    private final List<Map.Entry<MenuItem, Integer>> cartItems;

    private CancelItemsUI(List<Map.Entry<MenuItem, Integer>> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * CancelItemsUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param cartItems 장바구니 아이템 목록
     * @return CancelItemsUI 인스턴스
     */
    public static CancelItemsUI withParameter(List<Map.Entry<MenuItem, Integer>> cartItems) {
        return new CancelItemsUI(cartItems);
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
        int index = IntScanner.withFilter(filter);
        if (index == 0) {
            choice = new CancelItemsChoice.GoBack();
        } else if (index == allCancelIndex) {
            choice = new CancelItemsChoice.CancelAll();
        } else {
            choice = new CancelItemsChoice.CancelThis(cartItems.get(index - cartItemsStartIndex).getKey());
        }
    }
}
