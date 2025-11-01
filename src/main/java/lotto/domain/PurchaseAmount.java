package lotto.domain;

public class PurchaseAmount {

    private static final int UNIT = 1000;
    private final int amount;

    private PurchaseAmount(int amount) {
        validatePositive(amount);
        validateUnit(amount);
        this.amount = amount;
    }

    public static PurchaseAmount of(String amountInput) {
        validateNotBlank(amountInput);
        int amount = parseToInt(amountInput);
        return new PurchaseAmount(amount);
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.strip().isBlank()) {
            throw new IllegalArgumentException("[ERROR] 구입 금액을 입력해 주세요.");
        }
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양의 정수여야 합니다.");
        }
    }

    private void validateUnit(int amount) {
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public int value() {
        return amount;
    }
}
