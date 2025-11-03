package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String PURCHASE_MESSAGE = "개를 구매했습니다.";

    public static final String RESULT_FORMAT = """
                3개 일치 (5,000원) - %d개
                4개 일치 (50,000원) - %d개
                5개 일치 (1,500,000원) - %d개
                5개 일치, 보너스 볼 일치 (30,000,000원) - %d개
                6개 일치 (2,000,000,000원) - %d개
                """;

    public void printPurchaseCount(int count) {
        System.out.println();
        System.out.println(count + PURCHASE_MESSAGE);
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
        System.out.printf(RESULT_FORMAT,
                rankCount.getOrDefault(LottoRank.FIFTH, 0),
                rankCount.getOrDefault(LottoRank.FOURTH, 0),
                rankCount.getOrDefault(LottoRank.THIRD, 0),
                rankCount.getOrDefault(LottoRank.SECOND, 0),
                rankCount.getOrDefault(LottoRank.FIRST, 0)
                );
    }

    public void printException(String message) {
        System.out.println(message);
    }
}
