package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.constant.UiMessage;

public class InputView {

    public String requestMoney() {
        System.out.println(UiMessage.REQUEST_PURCHASE_AMOUNT);
        return Console.readLine();
    }

    public String requestWinNumber() {
        System.out.println();
        System.out.println(UiMessage.REQUEST_WINNING_NUMBERS);
        return Console.readLine();
    }

    public String requestBonusNumber() {
        System.out.println();
        System.out.println(UiMessage.REQUEST_BONUS_NUMBER);
        return Console.readLine();
    }
}
