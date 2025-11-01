package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;

public final class AppConfig {

    public LottoController getLottoController() {
        return new LottoController(getInputView());
    }

    private InputView getInputView() {
        return new InputView();
    }
}
