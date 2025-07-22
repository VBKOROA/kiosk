package kiosk.ui.choice;

import java.util.List;

import kiosk.exception.RidiculousException;
import kiosk.model.MenuItem;
import kiosk.model.choice.MenuSelectChoice;
import kiosk.util.IntScanner;
import kiosk.util.validator.ValidationFilter;
import kiosk.util.validator.XToYFilter;

public class MenuSelectUI extends AbstractChoiceable {
    private final List<MenuItem> items;

    private MenuSelectUI(List<MenuItem> items) {
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
                parameter.items());
    }

    /**
     * 가능한 음식 메뉴들을 표시하고 사용자의 선택을 입력 받음.
     */
    @Override
    public void display() throws RidiculousException {
        final int menuItemStartIndex = 1;
        final int lastIndex = items.size();

        System.out.println();
        System.out.println("[ SHAKESHACK MENU ]");

        for (int i = menuItemStartIndex; i <= items.size(); i++) {
            System.out.println(i + ". " + items.get(i - menuItemStartIndex));
        }

        System.out.println("0. 돌아가기");
        ValidationFilter filter = XToYFilter.range(0, lastIndex);
        int index = IntScanner.withFilter(filter);
        if (index == 0) {
            choice = new MenuSelectChoice.GoBack();
        } else if (index >= menuItemStartIndex && index <= lastIndex) {
            choice = new MenuSelectChoice.SelectThis(items.get(index - menuItemStartIndex));
        } else {
            // 비정상적인 입력 (filter에서 처리 되지 않은 비정상적인 상황)
            throw new RidiculousException();
        }
    }

    /**
     * MenuSelectUI의 파라미터 DTO 클래스.
     * 
     * @param items 메뉴 아이템 목록
     */
    public static record ParameterDto(List<MenuItem> items) {
    }
}
