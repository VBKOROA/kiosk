package kiosk.ui.choice;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kiosk.model.MenuItem;
import kiosk.util.IntScanner;

public class CancelItemsUI extends AbstractChoiceable {
    private final List<Map.Entry<MenuItem, Integer>> cartItems;

    private CancelItemsUI(Scanner sc, List<Map.Entry<MenuItem, Integer>> cartItems) {
        super(sc);
        this.cartItems = cartItems;
    }

    /**
     * CancelItemsUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return CancelItemsUI 인스턴스
     */
    public static CancelItemsUI withParameter(ParameterDto parameter) {
        return new CancelItemsUI(
                parameter.sc(),
                parameter.cartItems());
    }

    /**
     * 취소할 아이템 목록을 표시하고 사용자의 선택을 처리한다.
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("[ Cancel ]");
        displayCartItems(cartItems);
        System.out.println(cartItems.size() + 1 + ". 전체 취소");
        System.out.println("0. 돌아가기");
        System.out.println();
        choice = IntScanner.withFilter(sc, x -> x >= 0 && x <= cartItems.size() + 1);
    }

    private void displayCartItems(List<Map.Entry<MenuItem, Integer>> cartItems) {
        int idx = 1;
        for (var entry : cartItems) {
            System.out.println(idx++ + ". " + entry.getKey() + " | " + entry.getValue() + "개");
        }
    }

    /**
     * CancelItemsUI의 파라미터 DTO 클래스.
     * 
     * @param sc
     * @param cartItems 장바구니 아이템 목록
     */
    public static record ParameterDto(
            Scanner sc,
            List<Map.Entry<MenuItem, Integer>> cartItems) {
    }
}
