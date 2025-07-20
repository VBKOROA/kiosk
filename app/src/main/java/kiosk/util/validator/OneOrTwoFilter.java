package kiosk.util.validator;

public class OneOrTwoFilter implements ValidationFilter {
    /**
     * 입력값이 1 또는 2인지 확인하는 메소드.
     *
     * @param input 입력값
     * @return 입력값이 1 또는 2이면 true, 그렇지 않으면 false
     */
    @Override
    public boolean validate(int input) {
        return input == 1 || input == 2;
    }
}
