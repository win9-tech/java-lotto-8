package lotto.controller;

import lotto.domain.LottoStore;
import lotto.domain.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;


public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoStore lottoStore;

    public LottoController(InputView inputView, OutputView outputview, LottoStore lottoStore) {
        this.inputView = inputView;
        this.outputView = outputview;
        this.lottoStore = lottoStore;
    }

    public void run() {
        String amountInput = inputView.requestMoney();
        PurchaseAmount amount = PurchaseAmount.of(amountInput);
        lottoStore.createLotto(amount);
    }
}
