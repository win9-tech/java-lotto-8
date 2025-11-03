package lotto.controller;

import lotto.domain.*;
import lotto.util.parser.NumbersParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

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
        List<Lotto> lottoTickets = purchaseLottoWithRetry();
        WinLotto winLotto = createWinLottoWithRetry();
        compareResult(lottoTickets, winLotto);
        calculateProfitRate();
        displayResult();
    }

    private List<Lotto> purchaseLottoWithRetry() {
        while (true) {
            try {
                String amountInput = inputView.requestMoney();
                PurchaseAmount amount = PurchaseAmount.of(amountInput);
                List<Lotto> lottoTickets = lottoStore.createLotto(amount);
                outputView.printPurchaseCount(lottoTickets.size());
                outputView.printAllLottoNumbers(lottoTickets);
                return lottoTickets;
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.printException(e.getMessage());
            }
        }
    }

    private WinLotto createWinLottoWithRetry() {
        List<Integer> winNumbers = requestWinNumbersWithRetry();
        while (true) {
            try {
                int bonusNumber = requestBonusNumberWithRetry();
                return lottoMachine.drawingLotto(winNumbers, bonusNumber);
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.printException(e.getMessage());
            }
        }
    }

    private List<Integer> requestWinNumbersWithRetry() {
        while (true) {
            try {
                String winNumberInput = inputView.requestWinNumber();
                List<Integer> winNumbers = NumbersParser.parseWinningNumbers(winNumberInput);
                new Lotto(winNumbers);
                return winNumbers;
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.printException(e.getMessage());
            }
        }
    }

    private int requestBonusNumberWithRetry() {
        while (true) {
            try {
                String bonusNumberInput = inputView.requestBonusNumber();
                return NumbersParser.parseBonusNumber(bonusNumberInput);
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.printException(e.getMessage());
            }
        }
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
}