package lotto.domain;

import lotto.util.constant.ErrorMessages;
import lotto.util.constant.LottoRules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessages.WIN_DUPLICATE);
        }
    }
}
