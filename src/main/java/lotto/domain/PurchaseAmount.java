package lotto.domain;

import lotto.common.constant.ErrorMessages;
import lotto.common.constant.LottoRules;

public class PurchaseAmount {

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
            throw new IllegalArgumentException(ErrorMessages.EMPTY_PURCHASE);
        }
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PURCHASE_NUMBER);
        }
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessages.NEGATIVE_PURCHASE);
        }
    }

    private void validateUnit(int amount) {
        if (amount % LottoRules.PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_UNIT);
        }
    }

    public int value() {
        return amount;
    }
}
