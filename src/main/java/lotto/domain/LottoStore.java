package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.util.constant.LottoRules;

import static lotto.util.constant.LottoRules.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore {

    public List<Lotto> createLotto(PurchaseAmount amount) {
        int lottoCount = calculateLottoCount(amount.value());
        return IntStream.range(0, lottoCount)
                .mapToObj(index -> new Lotto(createRandomNumbers()))
                .collect(Collectors.toList());
    }

    private int calculateLottoCount(int amount) {
        return amount / LottoRules.PRICE_PER_TICKET;
    }

    private List<Integer> createRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN, MAX, PICK_COUNT);
    }
}
