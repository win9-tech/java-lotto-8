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
        lottoList.forEach(lotto -> updateRank(lotto, winLotto));
    }

    private void updateRank(Lotto lotto, WinLotto winLotto) {
        LottoRank rank = determineRank(lotto, winLotto);
        rankCounts.put(rank, rankCounts.get(rank) + 1);
    }

    private LottoRank determineRank(Lotto lotto, WinLotto winLotto) {
        int count = compareWinNumber(lotto, winLotto);
        boolean bonus = compareBonusNumber(lotto, winLotto);

        if (count == 6) return LottoRank.FIRST;
        if (count == 5 && bonus) return LottoRank.SECOND;
        if (count == 5) return LottoRank.THIRD;
        if (count == 4) return LottoRank.FOURTH;
        if (count == 3) return LottoRank.FIFTH;
        return LottoRank.NONE;
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
