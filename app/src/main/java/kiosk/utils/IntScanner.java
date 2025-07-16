package kiosk.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntScanner {
    public static int withFilter(Scanner sc, ValidationFilter filter) {
        try {
            int input = sc.nextInt();
            if (filter.validate(input)) {
                return input;
            }
            System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            sc.next(); // 잘못 입력된 버퍼를 청소
        }
        return withFilter(sc, filter); // 재귀 호출
    }

    @FunctionalInterface
    public interface ValidationFilter {
        boolean validate(int input);
    }
}
