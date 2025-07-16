package kiosk.models;

public record MenuItem(
        String name, // 이름
        double price, // 가격
        String description // 설명
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
}
