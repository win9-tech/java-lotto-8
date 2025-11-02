package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    private static final String PURCHASE_MESSAGE = "개를 구매했습니다.";

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
}
