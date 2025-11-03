package lotto.generator;

import java.util.List;

public interface LottoNumberGenerator {
    List<Integer> generate(int min, int max, int count);
}