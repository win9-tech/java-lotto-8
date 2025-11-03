package lotto.domain;

import java.util.Map;

public class Calculator {

    private double record;

    public void getRateOfReturn(Map<LottoRank, Integer> rankCount) {
        int count = rankCount.values().stream()
                .mapToInt(i -> i)
                .sum();

        int profit = rankCount.entrySet().stream()
                .mapToInt(entry -> entry.getKey().reward * entry.getValue())
                .sum();

        int totalInvestment = 1000 * count;

        record = (double) profit / totalInvestment * 100;
    }

    public double getRecord() {
        return record;
    }
}