package kiosk.ui.display;

import kiosk.ui.common.Displayable;

public class ExitUI implements Displayable {
    /**
     * 프로그램 종료 메시지를 출력
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("프로그램을 종료합니다.");
    }
}
