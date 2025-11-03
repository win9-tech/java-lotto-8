package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record WinLotto(List<Integer> lotto, int bonus) {

    public WinLotto {
        validate(lotto, bonus);
    }

    private void validate(List<Integer> lotto, int bonus) {
        if (lotto.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }

        if (lotto.stream().anyMatch(num -> num < 1 || num > 45)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1~45 범위여야 합니다.");
        }

        Set<Integer> unique = new HashSet<>(lotto);
        if (unique.size() != lotto.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복된 숫자가 있습니다.");
        }

        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 범위여야 합니다.");
        }

        if (unique.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}