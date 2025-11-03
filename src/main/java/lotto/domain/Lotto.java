package lotto.domain;

import lotto.common.constant.ErrorMessages;
import lotto.common.constant.LottoRules;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LottoRules.PICK_COUNT) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_COUNT);
        }
    }
}
