package kiosk.model;

import java.math.BigDecimal;

import kiosk.category.MenuCategory;

public record MenuItem(
        String name, // 이름
        BigDecimal price, // 가격
        String description, // 설명
        MenuCategory category // 카테고리
) {
    @Override
    public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name)
                .append(" | W ")
                .append(price)
                .append(" | ")
                .append(description);
            return sb.toString();
    }

    public static class Builder {
        private String name;
        private BigDecimal price;
        private String description;
        private MenuCategory category;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCategory(MenuCategory category) {
            this.category = category;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(name, price, description, category);
        }
    }
}
