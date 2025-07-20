package kiosk.ui.choice;

import java.util.List;
import java.util.Scanner;

import kiosk.model.MenuItem;
import kiosk.util.IntScanner;
import kiosk.util.validator.ValidationFilter;
import kiosk.util.validator.XToYFilter;

public class MenuSelectUI extends AbstractChoiceable {
    private final List<MenuItem> items;

    private MenuSelectUI(List<MenuItem> items, Scanner sc) {
        super(sc);
        this.items = items;
    }

    /**
     * MenuSelectUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param parameter
     * @return MenuSelectUI 인스턴스
     */
    public static MenuSelectUI withParameter(ParameterDto parameter) {
        return new MenuSelectUI(
                parameter.items(),
                parameter.sc());
    }

    /**
     * 가능한 음식 메뉴들을 표시하고 사용자의 선택을 입력 받음.
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("[ SHAKESHACK MENU ]");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i));
        }

        System.out.println("0. 종료 | 종료");
        ValidationFilter filter = XToYFilter.range(0, items.size());
        choice = IntScanner.withFilter(sc, filter);
    }

    /**
     * MenuSelectUI의 파라미터 DTO 클래스.
     * 
     * @param items  메뉴 아이템 목록
     * @param filter 입력 필터
     * @param sc
     */
    public static record ParameterDto(
            List<MenuItem> items,
            ValidationFilter filter,
            Scanner sc) {}
}
