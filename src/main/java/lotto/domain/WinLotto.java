package lotto.domain;

import lotto.common.constant.ErrorMessages;
import lotto.common.constant.LottoRules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record WinLotto(List<Integer> lotto, int bonus) {

    public WinLotto {
        validate(lotto, bonus);
    }

    private void validate(List<Integer> lotto, int bonus) {
        if (lotto.size() != LottoRules.PICK_COUNT) {
            throw new IllegalArgumentException(ErrorMessages.WIN_INVALID_COUNT);
        }

        if (lotto.stream().anyMatch(num -> num < LottoRules.MIN || num > LottoRules.MAX)) {
            throw new IllegalArgumentException(ErrorMessages.WIN_OUT_OF_RANGE);
        }

        Set<Integer> unique = new HashSet<>(lotto);
        if (unique.size() != lotto.size()) {
            throw new IllegalArgumentException(ErrorMessages.WIN_DUPLICATE);
        }

        if (bonus < LottoRules.MIN || bonus > LottoRules.MAX) {
            throw new IllegalArgumentException(ErrorMessages.BONUS_OUT_OF_RANGE);
        }

        if (unique.contains(bonus)) {
            throw new IllegalArgumentException(ErrorMessages.BONUS_DUPLICATE_WITH_WIN);
        }
    }
}