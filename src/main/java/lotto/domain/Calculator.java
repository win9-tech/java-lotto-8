package lotto.domain;

import lotto.common.constant.LottoRules;

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

        int totalInvestment = LottoRules.PRICE_PER_TICKET * count;

        record = (double) profit / totalInvestment * 100;
    }

    public double getRecord() {
        return record;
    }
}