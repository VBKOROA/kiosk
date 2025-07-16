package kiosk.models;

public record MenuItem(
        String name, // 이름
        double price, // 가격
        String description // 설명
) {}
