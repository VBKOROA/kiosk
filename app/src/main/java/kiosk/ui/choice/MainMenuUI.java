package kiosk.ui.choice;

import java.util.Scanner;

import kiosk.category.MenuCategory;
import kiosk.util.IntScanner;

public class MainMenuUI extends AbstractChoiceable {
    private final MenuCategory[] categories;
    private final boolean canOrder;

    private MainMenuUI(Scanner sc, MenuCategory[] categories, boolean canOrder) {
        super(sc);
        this.categories = categories;
        this.canOrder = canOrder;
    }

    /**
     * MainMenuUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return MainMenuUI 인스턴스
     */
    public static MainMenuUI withParameter(ParameterDto parameter) {
        return new MainMenuUI(
                parameter.sc(),
                parameter.categories(),
                parameter.canOrder());
    }

    /**
     * 메인 메뉴를 표시하고 사용자의 선택을 처리한다.
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("[ MAIN MENU ]");

        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }

        int lastIndex = categories.length;

        if (canOrder) {
            orderMenuUi(lastIndex);
            lastIndex += 2;
        }

        System.out.println("0. 종료");
        // 람다는 final 혹은 effectively final값만 사용할 수 있음
        final int lastIndexFinal = lastIndex;
        choice = IntScanner.withFilter(sc, x -> x >= 0 && x <= lastIndexFinal);
    }

    private void orderMenuUi(int lastIndex) {
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println(lastIndex + 1 + ". Orders");
        System.out.println(lastIndex + 2 + ". Cancel");
    }

    /**
     * MainMenuUI의 파라미터 DTO 클래스.
     * 
     * @param sc
     * @param categories 메뉴 카테고리 배열
     * @param canOrder   주문 가능 여부
     */
    public static record ParameterDto(
            Scanner sc,
            MenuCategory[] categories,
            boolean canOrder) {
    }
}
