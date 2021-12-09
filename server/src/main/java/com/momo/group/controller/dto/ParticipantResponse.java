package com.momo.group.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantResponse {

    private Long userId;

    private String image;

    private String nickname;

    private Long attendanceRate;

    @QueryProjection
    public ParticipantResponse(Long userId, String image, String nickname, Long attendanceRate) {
        this.userId = userId;
        this.image = image;
        this.nickname = nickname;
        this.attendanceRate = attendanceRate;
    }

    /*
    TODO : 개선 필요
    쿼리에서 유저가 참석한 일정 수를 조회해서 attendanceCnt에 저장한 후 출석률을 다시 계산해 attendanceCnt에 다시 저장한다.
    */
    public void calculateAttendanceRate(Long scheduleCnt) {
        if (this.attendanceRate != 0) {
            this.attendanceRate = scheduleCnt / this.attendanceRate;
        }
    }
}
