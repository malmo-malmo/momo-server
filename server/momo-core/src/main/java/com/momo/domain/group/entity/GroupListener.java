package com.momo.domain.group.entity;

import com.momo.domain.achievementrate.entity.GroupAchievementRate;
import com.momo.domain.achievementrate.repository.GroupAchievementRateRepository;
import com.momo.domain.common.util.BeanUtil;
import javax.persistence.PrePersist;

public class GroupListener {

    @PrePersist
    public void prePersist(Object o) {
        GroupAchievementRateRepository repository = BeanUtil.getBean(GroupAchievementRateRepository.class);

        Group group = (Group) o;
        GroupAchievementRate groupAchievementRate = GroupAchievementRate.create();

        group.updateAchievementRate(groupAchievementRate);
        repository.save(groupAchievementRate);
    }
}
