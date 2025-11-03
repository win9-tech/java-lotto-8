package lotto.domain;

import lotto.util.constant.LottoRules;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinLottoTest {

    @DisplayName("정상 당첨 번호/보너스 생성")
    @Test
    void 정상_생성() {
        assertThatCode(() -> new WinLotto(List.of(1,2,3,4,5,6), 7))
                .doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호가 6개가 아니면 예외")
    @Test
    void 개수_예외() {
        assertThatThrownBy(() -> new WinLotto(List.of(1,2,3,4,5), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 범위를 벗어나면 예외")
    @Test
    void 범위_예외() {
        assertThatThrownBy(() -> new WinLotto(List.of(0,2,3,4,5,6), 7))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new WinLotto(List.of(1,2,3,4,5,46), 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복이 있으면 예외")
    @Test
    void 중복_예외() {
        assertThatThrownBy(() -> new WinLotto(List.of(1,2,3,4,5,5), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외")
    @Test
    void 보너스_범위_예외() {
        assertThatThrownBy(() -> new WinLotto(List.of(1,2,3,4,5,6), LottoRules.MIN - 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new WinLotto(List.of(1,2,3,4,5,6), LottoRules.MAX + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외")
    @Test
    void 보너스_중복_예외() {
        assertThatThrownBy(() -> new WinLotto(List.of(1,2,3,4,5,6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}