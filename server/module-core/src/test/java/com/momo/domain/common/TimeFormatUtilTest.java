package com.momo.domain.common;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.common.util.TimeFormatUtil;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("시간 포맷 유틸 테스트")
public class TimeFormatUtilTest {

    @Test
    void 시간이_1분이_안_지났으면_방금_전으로_표시된다() {
        String msg = TimeFormatUtil.generateDateInfo(LocalDateTime.now());
        assertThat(msg).isEqualTo("방금 전");
    }

    @Test
    void 시간이_1시간_이내이면_몇분_전으로_표시된다() {
        int beforeMinute = 30;
        String msg = TimeFormatUtil.generateDateInfo(LocalDateTime.now().minusMinutes(beforeMinute));
        assertThat(msg).isEqualTo(beforeMinute + "분 전");
    }

    @Test
    void 시간이_1년_이내이면_몇개월_전으로_표시된다() {
        int beforeMonth = 10;
        String msg = TimeFormatUtil.generateDateInfo(LocalDateTime.now().minusMonths(beforeMonth));
        assertThat(msg).isEqualTo(beforeMonth + "개월 전");
    }

    @Test
    void 시간이_1년_이상이면_몇년_전으로_표시된다() {
        int beforeYear = 3;
        String msg = TimeFormatUtil.generateDateInfo(LocalDateTime.now().minusYears(beforeYear));
        assertThat(msg).isEqualTo(beforeYear + "년 전");
    }
}
