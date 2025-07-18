package kiosk.ui.display;

import java.math.BigDecimal;
import java.util.Scanner;

import kiosk.ui.common.Displayable;

public class CompleteOrderUI implements Displayable {
    private final BigDecimal totalPrice;
    private final Scanner sc;

    private CompleteOrderUI(Scanner sc, BigDecimal totalPrice) {
        this.sc = sc;
        this.totalPrice = totalPrice;
    }

    /**
     * CompleteOrderUI의 인스턴스를 생성하는 팩토리 메서드.
     * 
     * @param sc
     * @param totalPrice 최종 결제 금액
     * @return CompleteOrderUI 인스턴스
     */
    public static CompleteOrderUI withParameter(Scanner sc,
            BigDecimal totalPrice) {
        return new CompleteOrderUI(sc, totalPrice);
    }

    /**
     * 주문 완료 메시지와 총 결제 금액을 출력한다.
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("주문이 완료되었습니다.");
        System.out.println("총 결제 금액: W " + totalPrice);
        // 이전 숫자 입력시 생긴 \n 버퍼 삭제
        sc.nextLine();
        sc.nextLine();
    }
}
