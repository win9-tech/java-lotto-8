package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoStore;
import lotto.view.InputView;
import lotto.view.OutputView;

public final class AppConfig {

    public LottoController getLottoController() {
        return new LottoController(getInputView(), getOutputview(), getLottoStore());
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
}
