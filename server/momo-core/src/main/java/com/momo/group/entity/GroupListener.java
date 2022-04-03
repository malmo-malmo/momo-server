package com.momo.group.entity;

import com.momo.common.util.BeanUtil;
import com.momo.achievementrate.entity.GroupAchievementRate;
import com.momo.achievementrate.repository.GroupAchievementRateRepository;
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
