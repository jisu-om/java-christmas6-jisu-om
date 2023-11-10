package christmas.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Menu {
    EPITIZER(Map.of(
            "양송이수프", 6_000L,
            "타파스", 5_500L,
            "시저샐러드", 8_000L
    )),
    MAIN(Map.of(
            "티본스테이크", 55_000L,
            "바비큐립", 54_000L,
            "해산물파스타", 35_000L,
            "크리스마스파스타", 25_000L
    )),
    DESSERT(Map.of(
            "초코케이크", 15_000L,
            "아이스크림", 5_000L
    )),
    DRINK(Map.of(
            "제로콜라", 3_000L,
            "레드와인", 60_000L,
            "샴페인", 25_000L
    )),
    NONE(Collections.emptyMap());

    private final Map<String, Long> items;

    Menu(Map<String, Long> items) {
        this.items = items;
    }

    public Menu findByMenuName(String menuName) {
        //TODO 메뉴 이름(ex. 양송이수프)으로 Menu 찾기
        return Menu.NONE;
    }

    public Menu findByMenuType(String menuType) {
        //TODO 메인인지 디저트인지 음료인지 분류해서 리턴
        return Menu.NONE;
    }

    public Map<String, Long> getItems() {
        return items;
    }
}
