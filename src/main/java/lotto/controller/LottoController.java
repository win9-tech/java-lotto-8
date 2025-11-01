package lotto.controller;

import lotto.domain.PurchaseAmount;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        String amountInput = inputView.requestMoney();
        PurchaseAmount amount = PurchaseAmount.of(amountInput);
    }
}
