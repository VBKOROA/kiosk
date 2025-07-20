package kiosk.ui.choice;

import java.util.Scanner;

import kiosk.category.SaleCategory;
import kiosk.util.IntScanner;
import kiosk.util.validator.ValidationFilter;
import kiosk.util.validator.XToYFilter;

public class DiscountMenuUI extends AbstractChoiceable {
    private final SaleCategory[] saleCategories;

    private DiscountMenuUI(Scanner sc, SaleCategory[] saleCategories) {
        super(sc);
        this.saleCategories = saleCategories;
    }

    /**
     * DiscountMenuUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param sc
     * @param saleCategories 할인 카테고리 목록
     * @return DiscountMenuUI 인스턴스
     */
    public static DiscountMenuUI withParameter(Scanner sc,
            SaleCategory[] saleCategories) {
        return new DiscountMenuUI(sc, saleCategories);
    }

    /**
     * 할인 정보를 표시하고 사용자의 선택을 처리한다.
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("할인 정보를 입력해주세요.");
        for (int i = 0; i < saleCategories.length; i++) {
            System.out.println((i + 1) + ". " + saleCategories[i]);
        }
        System.out.println("0. 돌아가기");
        ValidationFilter filter = XToYFilter.range(0, saleCategories.length);
        choice = IntScanner.withFilter(sc, filter);
    }
}
