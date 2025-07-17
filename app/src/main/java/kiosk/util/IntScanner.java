package kiosk.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import kiosk.exception.InvalidInputException;

public class IntScanner {
    /**
     * 필터를 적용하여 유효한 정수를 입력받는다.
     * @param sc Scanner 객체
     * @param filter 필터 함수
     * @return 유효한 정수
     */
    public static int withFilter(Scanner sc, ValidationFilter filter) {
        while (true) {
            try {
                return readIntWithFilter(sc, filter);
            } catch (InvalidInputException e) {
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                sc.next(); // 잘못 입력된 버퍼를 청소
            }
        }
    }

    private static int readIntWithFilter(Scanner sc, ValidationFilter filter) throws InvalidInputException, InputMismatchException {
        int input = sc.nextInt();
        if (filter.validate(input)) {
            return input;
        } else {
            throw new InvalidInputException();
        }
    }

    @FunctionalInterface
    public interface ValidationFilter {
        boolean validate(int input);
    }
}
