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
        try {
            List<Lotto> lottoTickets = purchaseLotto();
            WinLotto winLotto = createWinLotto();
            compareResult(lottoTickets, winLotto);
            calculateProfitRate();
            displayResult();
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
        }
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

        List<Integer> winNumbers = NumbersParser.parseWinningNumbers(winNumberInput);
        int bonusNumber = NumbersParser.parseBonusNumber(bonusNumberInput);

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
}
