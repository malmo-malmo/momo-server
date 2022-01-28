package com.momo.domain.group.entity;

import com.momo.domain.common.entity.BaseEntity;
import com.momo.domain.user.entity.User;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = ParticipantListener.class)
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "group_tb_fk_participant"))
    private Group group;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_fk_participant"))
    private User user;


    /*
    TODO
    참여자 목록 조회 API 수정 필요 
    participantAchievementRate 테이블을 생성했으니 아래 필드들은 사라져도 되지 않을까?
    */

    @Formula("(select count(1) from schedule s where s.group_id = group_id and s.attendance_check = true)")
    private int scheduleCount;

    @Formula("(select count(1) from attendance a where a.group_id = group_id and a.user_id = user_id and a.is_attend = true)")
    private int attendanceCount;

    @Transient
    private int attendanceRate;

    @Builder
    public Participant(Long id, Group group, User user, int scheduleCount, int attendanceCount, int attendanceRate) {
        this.id = id;
        this.group = group;
        this.user = user;
        this.scheduleCount = scheduleCount;
        this.attendanceCount = attendanceCount;
        this.attendanceRate = attendanceRate;
    }

    public static Participant create(User user, Group group) {
        return Participant.builder()
            .user(user)
            .group(group)
            .build();
    }

    public void calculateAttendanceRate() {
        // TODO : 백분율 전략 패턴으로 변경 필요 - Strategy Pattern
        if (scheduleCount == 0) {
            attendanceRate = 0;
            return;
        }
        attendanceRate = (int) ((double) attendanceCount / scheduleCount * 100);
    }
}
