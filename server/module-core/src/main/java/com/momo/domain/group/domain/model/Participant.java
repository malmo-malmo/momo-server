package com.momo.domain.group.domain.model;

import com.momo.domain.common.domain.BaseEntity;
import com.momo.domain.user.domain.model.User;
import javax.persistence.Entity;
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
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "groups_fk_participant"))
    private Groups group;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_fk_participant"))
    private User user;

    @Formula("(select count(1) from schedule s where s.group_id = group_id and s.attendance_check = true)")
    private int scheduleCount;

    @Formula("(select count(1) from attendance a where a.group_id = group_id and a.user_id = user_id and a.is_attend = true)")
    private int attendanceCount;

    @Transient
    private int attendanceRate;

    @Builder
    public Participant(Long id, User user, Groups group) {
        this.id = id;
        this.user = user;
        this.group = group;
    }

    public static Participant create(User user, Groups group) {
        return Participant.builder()
            .user(user)
            .group(group)
            .build();
    }

    public void calculateAttendanceRate() {
        //TODO: 따로 Util 클래스 필요?
        if (scheduleCount == 0) {
            attendanceRate = 0;
            return;
        }
        attendanceRate = attendanceCount / scheduleCount * 100;
    }
}
