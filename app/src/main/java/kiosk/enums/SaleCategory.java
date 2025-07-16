package kiosk.enums;

import java.math.BigDecimal;

public enum SaleCategory {
    국가유공자("국가유공자", BigDecimal.valueOf(0.1)),
    군인("군인", BigDecimal.valueOf(0.05)),
    학생("학생", BigDecimal.valueOf(0.03));

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
