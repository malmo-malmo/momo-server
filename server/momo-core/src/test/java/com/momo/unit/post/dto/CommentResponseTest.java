package com.momo.unit.post.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.post.dto.CommentResponse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 응답 테스트")
public class CommentResponseTest {

    @Test
    void 작성일자가_1분이_안_지났으면_방금_전으로_표시된다() {
        CommentResponse response = CommentResponse.builder()
            .createdDate(LocalDateTime.now())
            .build();
        assertThat(response.getCreatedDate()).isEqualTo("방금 전");
    }

    @Test
    void 작성일자가_1시간_이내이면_몇분_전으로_표시된다() {
        int beforeMinute = 30;
        CommentResponse response = CommentResponse.builder()
            .createdDate(LocalDateTime.now().minusMinutes(beforeMinute))
            .build();
        assertThat(response.getCreatedDate()).isEqualTo(beforeMinute + "분 전");
    }

    @Test
    void 작성일자가_1년_이내이면_몇개월_전으로_표시된다() {
        int beforeMonth = 10;
        CommentResponse response = CommentResponse.builder()
            .createdDate(LocalDateTime.now().minusMonths(beforeMonth))
            .build();
        assertThat(response.getCreatedDate()).isEqualTo(beforeMonth + "개월 전");
    }

    @Test
    void 작성일자가_1년_이상이면_몇년_전으로_표시된다() {
        int beforeYear = 3;
        CommentResponse response = CommentResponse.builder()
            .createdDate(LocalDateTime.now().minusYears(beforeYear))
            .build();
        assertThat(response.getCreatedDate()).isEqualTo(beforeYear + "년 전");
    }
}
