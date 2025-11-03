package lotto.view;

import lotto.util.constant.UiMessage;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchaseCount(int count) {
        System.out.println();
        System.out.println(count + UiMessage.PURCHASE_MESSAGE);
    }

    public void printAllLottoNumbers(List<Lotto> lottoTickets) {
        lottoTickets.stream()
                .map(lotto -> lotto.getNumbers()
                        .stream()
                        .sorted()
                        .toList())
                .forEach(System.out::println);
    }

    public void printResult(Map<LottoRank, Integer> rankCount, double record) {
        System.out.println();
        System.out.printf(UiMessage.RESULT_FORMAT,
                rankCount.getOrDefault(LottoRank.FIFTH, 0),
                rankCount.getOrDefault(LottoRank.FOURTH, 0),
                rankCount.getOrDefault(LottoRank.THIRD, 0),
                rankCount.getOrDefault(LottoRank.SECOND, 0),
                rankCount.getOrDefault(LottoRank.FIRST, 0),
                record);
    }

    public void printException(String message) {
        System.out.println(message);
    }
}
