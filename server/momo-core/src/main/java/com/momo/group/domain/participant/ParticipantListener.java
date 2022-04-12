package com.momo.group.domain.participant;

import com.momo.common.util.BeanUtil;
import com.momo.achievementrate.entity.ParticipantAchievementRate;
import com.momo.achievementrate.repository.ParticipantAchievementRateRepository;
import javax.persistence.PrePersist;

public class ParticipantListener {

    @PrePersist
    public void prePersist(Object o) {
        ParticipantAchievementRateRepository repository = BeanUtil.getBean(ParticipantAchievementRateRepository.class);

        Participant participant = (Participant) o;
        ParticipantAchievementRate achievementRate = ParticipantAchievementRate.create();

        participant.updateAchievementRate(achievementRate);
        repository.save(achievementRate);
    }
}
