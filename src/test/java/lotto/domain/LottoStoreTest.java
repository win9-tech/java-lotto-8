package lotto.domain;

import lotto.generator.LottoNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StubGenerator implements LottoNumberGenerator {
    private final List<Integer> fixed;
    StubGenerator(List<Integer> fixed) { this.fixed = fixed; }
    @Override public List<Integer> generate(int min, int max, int count) { return fixed; }
}

class LottoStoreTest {

    @Test
    void 금액만큼_매수생성_및_번호확인() {
        // given
        var store = new LottoStore(new StubGenerator(List.of(1,2,3,4,5,6)));
        var amount = PurchaseAmount.of("3000");

        // when
        var tickets = store.createLotto(amount);

        // then
        assertThat(tickets).hasSize(3);

        assertThat(tickets.get(0).getNumbers()).containsExactly(1,2,3,4,5,6);
        assertThat(tickets.get(1).getNumbers()).containsExactly(1,2,3,4,5,6);
        assertThat(tickets.get(2).getNumbers()).containsExactly(1,2,3,4,5,6);
    }
}