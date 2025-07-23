package kiosk.ui.choice;

import kiosk.category.SaleCategory;
import kiosk.model.choice.DiscountMenuChoice;
import kiosk.util.IntScanner;
import kiosk.util.validator.ValidationFilter;
import kiosk.util.validator.XToYFilter;

/**
 * 할인 메뉴 UI를 담당하는 클래스
 * 사용자가 할인 카테고리를 선택할 수 있도록 화면을 표시하고 선택을 처리
 */
public class DiscountMenuUI extends AbstractChoiceable {
    private final SaleCategory[] saleCategories;

    private DiscountMenuUI(SaleCategory[] saleCategories) {
        this.saleCategories = saleCategories;
    }

    /**
     * DiscountMenuUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param saleCategories 할인 카테고리 목록
     * @return DiscountMenuUI 인스턴스
     */
    public static DiscountMenuUI withParameter(SaleCategory[] saleCategories) {
        return new DiscountMenuUI(saleCategories);
    }

    /**
     * 할인 정보를 표시하고 사용자의 선택을 처리한다.
     */
    @Override
    public void display() {
        final int saleCategoriesStartIndex = 1;
        final int lastIndex = saleCategories.length;

        System.out.println();
        System.out.println("할인 정보를 입력해주세요.");

        for (int i = saleCategoriesStartIndex; i <= saleCategories.length; i++) {
            System.out.println(i + ". " + saleCategories[i - saleCategoriesStartIndex]);
        }
        System.out.println("0. 돌아가기");
        ValidationFilter filter = XToYFilter.range(0, lastIndex);
        int index = IntScanner.withFilter(filter);

        if (index == 0) {
            choice = new DiscountMenuChoice.GoBack();
        } else {
            choice = new DiscountMenuChoice.DiscountWithThis(saleCategories[index - saleCategoriesStartIndex]);
        }
    }
}
