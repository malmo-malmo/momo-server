package com.momo.unit.group.entity;

import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.entity.Group;
import com.momo.group.entity.Participant;
import com.momo.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Participant POJO 테스트")
public class ParticipantTest {

    private User user;
    private Group group;

    @BeforeEach
    void setUp() {
        user = getUserWithId();
        group = getGroupWithId(user);
    }

    @Test
    void 참여자_생성_테스트() {
        Participant expected = Participant.create(user, group);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getUser().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getGroup().getId()).isEqualTo(group.getId())
        );
    }
}
