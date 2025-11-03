package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoStore;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private static final String DELIMITER = ",";
    private static final String ERR_WIN_EMPTY = "[ERROR] 당첨 번호 입력이 비어 있습니다.";
    private static final String ERR_WIN_NAN   = "[ERROR] 당첨 번호는 숫자만 입력해야 합니다.";
    private static final String ERR_BONUS_EMPTY = "[ERROR] 보너스 번호 입력이 비어 있습니다.";
    private static final String ERR_BONUS_NAN   = "[ERROR] 보너스 번호는 숫자만 입력해야 합니다.";

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoStore lottoStore;
    private final LottoMachine lottoMachine;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoStore lottoStore,
            LottoMachine lottoMachine
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoStore = lottoStore;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        purchaseLotto();
        WinLotto winLotto = createWinLotto();
    }

    private void purchaseLotto() {
        String amountInput = inputView.requestMoney();
        PurchaseAmount amount = PurchaseAmount.of(amountInput);

        List<Lotto> lottoTickets = lottoStore.createLotto(amount);
        outputView.printPurchaseCount(lottoTickets.size());
        outputView.printAllLottoNumbers(lottoTickets);
    }

    private WinLotto createWinLotto() {
        String winNumberInput = inputView.requestWinNumber();
        String bonusNumberInput = inputView.requestBonusNumber();

        List<Integer> winNumbers = parseWinningNumbers(winNumberInput);
        int bonusNumber = parseBonusNumber(bonusNumberInput);

        return lottoMachine.drawingLotto(winNumbers, bonusNumber);
    }

    private List<Integer> parseWinningNumbers(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ERR_WIN_EMPTY);
        }
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERR_WIN_NAN);
        }
    }

    private int parseBonusNumber(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ERR_BONUS_EMPTY);
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERR_BONUS_NAN);
        }
    }
}