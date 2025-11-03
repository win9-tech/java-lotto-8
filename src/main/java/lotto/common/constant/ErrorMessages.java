package lotto.common.constant;

public final class ErrorMessages {

    public static final String ERR_WIN_EMPTY = "[ERROR] 당첨 번호 입력이 비어 있습니다.";
    public static final String ERR_WIN_NAN   = "[ERROR] 당첨 번호는 숫자만 입력해야 합니다.";
    public static final String ERR_BONUS_EMPTY = "[ERROR] 보너스 번호 입력이 비어 있습니다.";
    public static final String ERR_BONUS_NAN   = "[ERROR] 보너스 번호는 숫자만 입력해야 합니다.";
    public static final String INVALID_NUMBER_COUNT = "[ERROR] 로또 번호는 6개여야 합니다.";
    public static final String EMPTY_PURCHASE = "[ERROR] 구입 금액을 입력해 주세요.";
    public static final String INVALID_PURCHASE_NUMBER = "[ERROR] 구입 금액은 숫자여야 합니다.";
    public static final String NEGATIVE_PURCHASE = "[ERROR] 구입 금액은 양의 정수여야 합니다.";
    public static final String INVALID_UNIT = "[ERROR] 구입 금액은 1,000원 단위여야 합니다.";

    public static final String WIN_INVALID_COUNT = "[ERROR] 당첨 번호는 6개여야 합니다.";
    public static final String WIN_OUT_OF_RANGE = "[ERROR] 당첨 번호는 1~45 범위여야 합니다.";
    public static final String WIN_DUPLICATE = "[ERROR] 당첨 번호에 중복된 숫자가 있습니다.";

    public static final String BONUS_OUT_OF_RANGE = "[ERROR] 보너스 번호는 1~45 범위여야 합니다.";
    public static final String BONUS_DUPLICATE_WITH_WIN = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private ErrorMessages() {}
}