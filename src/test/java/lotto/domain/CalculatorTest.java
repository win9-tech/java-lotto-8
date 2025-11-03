package lotto.domain;

import lotto.util.constant.LottoRules;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @DisplayName("등수 집계를 통해 수익률을 계산한다")
    @Test
    void 수익률_계산() {
        // given
        Map<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        for (LottoRank r : LottoRank.values()) counts.put(r, 0);
        counts.put(LottoRank.FIFTH, 1);

        Calculator calculator = new Calculator();

        // when
        calculator.getRateOfReturn(counts);

        // then
        int totalTickets = counts.values().stream().mapToInt(i -> i).sum();
        int profit = LottoRank.FIFTH.reward;
        double expected = (double) profit / (LottoRules.PRICE_PER_TICKET * totalTickets) * 100;

        assertThat(calculator.getRecord()).isEqualTo(expected);
    }
}