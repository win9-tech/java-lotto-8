package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore {

    private static final int PRICE_PER_TICKET = 1000;

    public List<Lotto> createLotto(PurchaseAmount amount) {
        int lottoCount = calculateLottoCount(amount.value());
        return IntStream.range(0, lottoCount)
                .mapToObj(index -> new Lotto(createRandomNumbers()))
                .collect(Collectors.toList());
    }

    private int calculateLottoCount(int amount) {
        return amount / PRICE_PER_TICKET;
    }

    private List<Integer> createRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
