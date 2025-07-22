package kiosk.util;

import java.util.InputMismatchException;
import kiosk.exception.InvalidInputException;
import kiosk.util.validator.ValidationFilter;

public class IntScanner {
    /**
     * 필터를 적용하여 유효한 정수를 입력받는다.
     * @param filter 필터 함수
     * @return 유효한 정수
     */
    public static int withFilter(ValidationFilter filter) {
        while (true) {
            try {
                return readIntWithFilter(filter);
            } catch (InvalidInputException e) {
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                ScannerProvider.sc.next(); // 잘못 입력된 버퍼를 청소
            }
        }
    }

    private static int readIntWithFilter(ValidationFilter filter) throws InvalidInputException, InputMismatchException {
        int input = ScannerProvider.sc.nextInt();
        if (filter.validate(input)) {
            return input;
        } else {
            throw new InvalidInputException();
        }
    }
}
