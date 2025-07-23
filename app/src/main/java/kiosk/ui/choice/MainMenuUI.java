package kiosk.ui.choice;

import java.util.stream.IntStream;

import kiosk.category.MenuCategory;
import kiosk.exception.RidiculousException;
import kiosk.model.choice.MainMenuChoice;
import kiosk.util.IntScanner;
import kiosk.util.validator.ValidationFilter;
import kiosk.util.validator.XToYFilter;

/**
 * 메인 메뉴 UI를 담당하는 클래스
 * 사용 가능한 메뉴 카테고리와 주문 가능 여부를 기반으로
 * 메인 메뉴를 표시하고 사용자의 선택을 처리
 */
public class MainMenuUI extends AbstractChoiceable {
    private final MenuCategory[] categories;
    private final boolean canOrder;

    private MainMenuUI(MenuCategory[] categories, boolean canOrder) {
        this.categories = categories;
        this.canOrder = canOrder;
    }

    /**
     * 지정된 메뉴 카테고리와 주문 가능 여부로 {@link MainMenuUI}의 새 인스턴스를 생성
     *
     * @param categories 사용 가능한 메뉴 카테고리의 {@link MenuCategory} 배열
     * @param canOrder   주문이 가능한지 여부를 나타내는 boolean 값
     * @return 주어진 파라미터로 구성된 새로운 {@link MainMenuUI} 인스턴스
     */
    public static MainMenuUI withParameter(MenuCategory[] categories, boolean canOrder) {
        return new MainMenuUI(categories, canOrder);
    }

    /**
     * 메인 메뉴를 표시하고 사용자의 선택을 처리한다.
     */
    @Override
    public void display() throws RidiculousException {
        final int menuCategoriesStartIndex = 1;
        final int orderIndex = categories.length + 1;
        final int cancelIndex = categories.length + 2;

        System.out.println();
        System.out.println("[ MAIN MENU ]");

        IntStream.rangeClosed(menuCategoriesStartIndex, categories.length)
                .forEach(idx -> System.out.println(idx + ". " + categories[idx - menuCategoriesStartIndex]));

        int lastIndex = categories.length;

        if (canOrder) {
            orderMenuUi(orderIndex, cancelIndex);
            lastIndex += 2;
        }

        System.out.println("0. 종료");
        ValidationFilter filter = XToYFilter.range(0, lastIndex);
        int index = IntScanner.withFilter(filter);
        if (index == 0) {
            choice = new MainMenuChoice.Exit();
        } else if (index <= categories.length) {
            choice = new MainMenuChoice.GoToCategory(categories[index - menuCategoriesStartIndex]);
        } else if (index == orderIndex) {
            // Order를 선택했다면
            choice = new MainMenuChoice.Order();
        } else if (index == cancelIndex) {
            // Cancel을 선택했다면
            choice = new MainMenuChoice.CancelCartItems();
        } else {
            // 비정상적인 상황임
            throw new RidiculousException();
        }
    }

    private void orderMenuUi(int orderIndex, int cancelIndex) {
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println(orderIndex + ". Order");
        System.out.println(cancelIndex + ". Cancel");
    }
}
