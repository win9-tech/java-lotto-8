package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMatcherTest {

    @DisplayName("매칭 개수 및 보너스 여부로 등수를 판정하고 집계한다")
    @Test
    void 등수_판정_및_집계() {
        // given
        WinLotto win = new WinLotto(List.of(1,2,3,4,5,6), 7);
        LottoMatcher matcher = new LottoMatcher();

        Lotto first  = new Lotto(List.of(1,2,3,4,5,6));
        Lotto second = new Lotto(List.of(1,2,3,4,5,7));
        Lotto third  = new Lotto(List.of(1,2,3,4,5,8));
        Lotto fourth = new Lotto(List.of(1,2,3,4,9,10));
        Lotto fifth  = new Lotto(List.of(1,2,3,11,12,13));
        Lotto none   = new Lotto(List.of(8,9,10,11,12,13));

        List<Lotto> tickets = List.of(first, second, third, fourth, fifth, none);

        // when
        matcher.compareLotto(tickets, win);
        Map<LottoRank, Integer> counts = matcher.getRankCount();

        // then
        assertThat(counts.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(counts.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(counts.get(LottoRank.THIRD)).isEqualTo(1);
        assertThat(counts.get(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(counts.get(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(counts.get(LottoRank.NONE)).isEqualTo(1);
    }
}