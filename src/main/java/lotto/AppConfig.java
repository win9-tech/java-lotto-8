package lotto;

import lotto.controller.LottoController;
import lotto.domain.Calculator;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMatcher;
import lotto.domain.LottoStore;
import lotto.view.InputView;
import lotto.view.OutputView;

public final class AppConfig {

    public LottoController getLottoController() {
        return new LottoController(
                getInputView(),
                getOutputview(),
                getLottoStore(),
                getLottoMachine(),
                getLottoMatcher(),
                getCalculator()
        );
    }

    private InputView getInputView() {
        return new InputView();
    }

    private OutputView getOutputview() {
        return new OutputView();
    }

    private LottoStore getLottoStore() {
        return new LottoStore();
    }

    private LottoMachine getLottoMachine() {
        return new LottoMachine();
    }

    private LottoMatcher getLottoMatcher() {
        return new LottoMatcher();
    }

    private Calculator getCalculator() {
        return new Calculator();
    }
}
