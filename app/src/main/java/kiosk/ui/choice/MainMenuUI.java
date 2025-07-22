package kiosk.ui.choice;

import java.util.Scanner;
import java.util.stream.IntStream;

import kiosk.category.MenuCategory;
import kiosk.model.choice.MainMenuChoice;
import kiosk.util.IntScanner;
import kiosk.util.validator.ValidationFilter;
import kiosk.util.validator.XToYFilter;

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
        final int menuCategoriesStartIndex = 1;
        final int orderIndex = categories.length+1;
        final int cancelIndex = categories.length+2;

        System.out.println();
        System.out.println("[ MAIN MENU ]");

        IntStream.rangeClosed(menuCategoriesStartIndex, categories.length).forEach(idx -> 
            System.out.println(idx + ". " + categories[idx - menuCategoriesStartIndex])
        );

        int lastIndex = categories.length;

        if (canOrder) {
            orderMenuUi(orderIndex, cancelIndex);
            lastIndex += 2;
        }

        System.out.println("0. 종료");
        ValidationFilter filter = XToYFilter.range(0, lastIndex);
        int index = IntScanner.withFilter(sc, filter);
        if (index == 0) {
            choice = new MainMenuChoice.Exit();
        }
        else if (index <= categories.length) {
            choice = new MainMenuChoice.GoToCategory(categories[index - menuCategoriesStartIndex]);
        }
        else if(index == orderIndex) {
            // Order를 선택했다면
            choice = new MainMenuChoice.Order();
        } else if(index == cancelIndex) {
            // Cancel을 선택했다면
            choice = new MainMenuChoice.CancelCartItems();
        } else {
            // 비정상적인 상황임
            // TODO: 올바른 예외처리 추가
            choice = new MainMenuChoice.Exit();
        }
    }

    private void orderMenuUi(int orderIndex, int cancelIndex) {
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println(orderIndex + ". Order");
        System.out.println(cancelIndex + ". Cancel");
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
