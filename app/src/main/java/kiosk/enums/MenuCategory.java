package kiosk.enums;

public enum MenuCategory {
    BUGER("버거"),
    DRINK("음료"),
    SIDE("사이드");

    private final String name;

    MenuCategory(String name) {
        this.name = name;
    }
}
