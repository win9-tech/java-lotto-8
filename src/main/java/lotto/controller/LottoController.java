package lotto.controller;

import lotto.common.constant.ErrorMessages;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private static final String DELIMITER = ",";

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoStore lottoStore;
    private final LottoMachine lottoMachine;
    private final LottoMatcher lottoMatcher;
    private final Calculator calculator;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoStore lottoStore,
            LottoMachine lottoMachine,
            LottoMatcher lottoMatcher,
            Calculator calculator
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoStore = lottoStore;
        this.lottoMachine = lottoMachine;
        this.lottoMatcher = lottoMatcher;
        this.calculator = calculator;
    }

    public void run() {
        List<Lotto> lottoTickets = purchaseLotto();
        WinLotto winLotto = createWinLotto();
        compareResult(lottoTickets, winLotto);
        calculateProfitRate();
        displayResult();
    }

    private List<Lotto> purchaseLotto() {
        String amountInput = inputView.requestMoney();
        PurchaseAmount amount = PurchaseAmount.of(amountInput);

        List<Lotto> lottoTickets = lottoStore.createLotto(amount);
        outputView.printPurchaseCount(lottoTickets.size());
        outputView.printAllLottoNumbers(lottoTickets);

        return lottoTickets;
    }

    private WinLotto createWinLotto() {
        String winNumberInput = inputView.requestWinNumber();
        String bonusNumberInput = inputView.requestBonusNumber();

        List<Integer> winNumbers = parseWinningNumbers(winNumberInput);
        int bonusNumber = parseBonusNumber(bonusNumberInput);

        return lottoMachine.drawingLotto(winNumbers, bonusNumber);
    }

    private void compareResult(List<Lotto> lottoTickets, WinLotto winLotto) {
        lottoMatcher.compareLotto(lottoTickets, winLotto);
    }

    private void calculateProfitRate() {
        calculator.getRateOfReturn(lottoMatcher.getRankCount());
    }

    private void displayResult() {
        outputView.printResult(lottoMatcher.getRankCount(), calculator.getRecord());
    }

    private List<Integer> parseWinningNumbers(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.ERR_WIN_EMPTY);
        }
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.ERR_WIN_NAN);
        }
    }

    private int parseBonusNumber(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.ERR_BONUS_EMPTY);
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.ERR_BONUS_NAN);
        }
    }
}