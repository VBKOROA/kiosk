package kiosk.category;

import java.math.BigDecimal;

public enum SaleCategory {
    VETERAN("국가유공자", BigDecimal.valueOf(0.1)),
    SOLDIER("군인", BigDecimal.valueOf(0.05)),
    STUDENT("학생", BigDecimal.valueOf(0.03)),
    NONE("해당없음", BigDecimal.ZERO);

    private final String name;
    private final BigDecimal discountRate;

    SaleCategory(String name, BigDecimal discountRate) {
        this.name = name;
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return name;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }
}
