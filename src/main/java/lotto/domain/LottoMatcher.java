package lotto.domain;

import java.util.*;

public class LottoMatcher {

    private final Map<LottoRank, Integer> rankCounts = new EnumMap<>(LottoRank.class);

    public LottoMatcher() {
        for (LottoRank rank : LottoRank.values()) {
            rankCounts.put(rank, 0);
        }
    }

    public Map<LottoRank, Integer> getRankCount() {
        return Collections.unmodifiableMap(rankCounts);
    }

    public void compareLotto(List<Lotto> lottoList, WinLotto winLotto) {
    }

    private int compareWinNumber(Lotto lotto, WinLotto winLotto) {
        List<Integer> lottoNumber = lotto.getNumbers();
        List<Integer> winLottoNumber = winLotto.lotto();

        Set<Integer> lottoSet = new HashSet<>(lottoNumber);
        lottoSet.retainAll(winLottoNumber);

        return lottoSet.size();
    }

    private boolean compareBonusNumber(Lotto lotto, WinLotto winLotto) {
        return lotto.getNumbers().contains(winLotto.bonus());
    }
}