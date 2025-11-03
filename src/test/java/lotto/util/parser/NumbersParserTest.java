package lotto.util.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumbersParserTest {

    @DisplayName("당첨 번호 문자열을 정수 리스트로 파싱한다")
    @Test
    void 당첨번호_파싱_성공() {
        List<Integer> nums = NumbersParser.parseWinningNumbers("1, 2,3,4,5,6");
        assertThat(nums).containsExactly(1,2,3,4,5,6);
    }

    @DisplayName("당첨 번호가 비어있으면 예외")
    @Test
    void 당첨번호_빈값_예외() {
        assertThatThrownBy(() -> NumbersParser.parseWinningNumbers("  "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 숫자가 아닌 값이 있으면 예외")
    @Test
    void 당첨번호_숫자아님_예외() {
        assertThatThrownBy(() -> NumbersParser.parseWinningNumbers("1,2,a,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호 파싱 성공")
    @Test
    void 보너스번호_파싱_성공() {
        int bonus = NumbersParser.parseBonusNumber(" 7 ");
        assertThat(bonus).isEqualTo(7);
    }

    @DisplayName("보너스 번호가 비어있으면 예외")
    @Test
    void 보너스번호_빈값_예외() {
        assertThatThrownBy(() -> NumbersParser.parseBonusNumber(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 숫자가 아니면 예외")
    @Test
    void 보너스번호_숫자아님_예외() {
        assertThatThrownBy(() -> NumbersParser.parseBonusNumber("x"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}