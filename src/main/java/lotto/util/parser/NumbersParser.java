package lotto.util.parser;

import lotto.util.constant.ErrorMessages;

import java.util.Arrays;
import java.util.List;

public final class NumbersParser {

    public static final String DELIMITER = ",";

    private NumbersParser() {}

    public static List<Integer> parseWinningNumbers(String input) {
        if (input == null || input.isBlank()) throw new IllegalArgumentException(ErrorMessages.ERR_WIN_EMPTY);
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.ERR_WIN_NAN);
        }
    }

    public static int parseBonusNumber(String input) {
        if (input == null || input.isBlank()) throw new IllegalArgumentException(ErrorMessages.ERR_BONUS_EMPTY);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.ERR_BONUS_NAN);
        }
    }
}
