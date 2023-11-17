package christmas.view;

import christmas.domain.constants.BenefitConstants;

public class OutputViewMessageConstants {
    public static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String RESULT_SRART_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String MENU_TITLE = "<주문 메뉴>";
    public static final String MENU_FORMAT = "%s %d개";
    public static final String ORIGINAL_TOTAL_AMOUNT_TITLE = "<할인 전 총주문 금액>";
    public static final String TOTAL_AMOUNT_FORMAT = "%,d원";
    public static final String GIVE_AWAY_TITLE = "<증정 메뉴>";
    public static final String GIVE_AWAY_FORMAT = String.format("%s %d개", BenefitConstants.GIVE_AWAY_ITEM, BenefitConstants.GIVE_AWAY_QUANTITY);
    public static final String DEFAULT = "없음";
    public static final String MATCHING_EVENTS_TITLE = "<혜택 내역>";
    public static final String MATCHING_EVENT_FORMAT = "%s: -%,d원";
    public static final String TOTAL_BENEFIT_AMOUNT_TITLE = "<총혜택 금액>";
    public static final String TOTAL_BENEFIT_AMOUNT_FORMAT = "-%,d원";
    public static final String TOTAL_BENEFIT_AMOUNT_DEFAULT_MESSAGE = "0원";
    public static final String EXPECTED_TOTAL_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    public static final String BADGE_TITLE = "<12월 이벤트 배지>";
}