package lotto.domain;

import java.util.List;

public class LottoStore {

    private static final int PRICE_PER_TICKET = 1000;

    public List<Lotto> createLotto(PurchaseAmount amount) {
        int lottoCount = calculateLottoCount(amount.value());
        return null;
    }

    private int calculateLottoCount(int amount) {
        return amount / PRICE_PER_TICKET;
    }
}
