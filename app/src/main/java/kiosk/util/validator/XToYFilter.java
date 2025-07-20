package kiosk.util.validator;

public class XToYFilter implements ValidationFilter {
    private final int min;
    private final int max;

    /**
     * 생성자
     *
     * @param min 허용하는 최소값
     * @param max 허용하는 최대값
     */
    private XToYFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * 팩토리 메서드로, 주어진 범위(min ~ max) 내의 값만 허용하는 필터를 생성
     * 
     * @param min 허용하는 최소값
     * @param max 허용하는 최대값
     * @return XToYFilter 인스턴스
     */
    public static XToYFilter range(int min, int max) {
        return new XToYFilter(min, max);
    }

    @Override
    public boolean validate(int input) {
        // 입력값이 min과 max 사이에 있는지 확인
        return input >= min && input <= max;
    }
}
