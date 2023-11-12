package christmas.domain.event;

public enum EventNames {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAYS("평일 할인"),
    WEEKENDS("주말 할인"),
    SPECIAL("특별 할인"),
    GIVE_AWAY("증정 이벤트");

    private final String eventName;

    EventNames(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
