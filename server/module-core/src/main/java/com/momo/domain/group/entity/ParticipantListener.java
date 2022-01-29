package com.momo.domain.group.entity;

import com.momo.domain.achievementrate.entity.ParticipantAchievementRate;
import com.momo.domain.achievementrate.repository.ParticipantAchievementRateRepository;
import com.momo.domain.common.util.BeanUtil;
import javax.persistence.PrePersist;

public class ParticipantListener {

    @PrePersist
    public void prePersist(Object o) {
        ParticipantAchievementRateRepository repository = BeanUtil.getBean(ParticipantAchievementRateRepository.class);
        Participant participant = (Participant) o;
        repository.save(ParticipantAchievementRate.create(participant));
    }
}
