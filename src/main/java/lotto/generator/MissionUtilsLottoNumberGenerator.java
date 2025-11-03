package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class MissionUtilsLottoNumberGenerator implements LottoNumberGenerator {
    @Override
    public List<Integer> generate(int min, int max, int count) {
        return Randoms.pickUniqueNumbersInRange(min, max, count);
    }
}