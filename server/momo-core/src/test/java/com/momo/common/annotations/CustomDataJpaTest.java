package com.momo.common.annotations;

import static com.momo.Profile.TEST;

import com.momo.domain.achievementrate.repository.GroupAchievementRateRepository;
import com.momo.domain.achievementrate.repository.ParticipantAchievementRateRepository;
import com.momo.domain.district.repository.DistrictRepository;
import com.momo.domain.favorite.repository.FavoriteGroupRepository;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.post.repository.CommentRepository;
import com.momo.domain.post.repository.ImageRepository;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.schedule.repository.AttendanceRepository;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.user.repository.UserRepository;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
@EnableJpaAuditing
@ActiveProfiles(TEST)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = {
    UserRepository.class, AttendanceRepository.class, ScheduleRepository.class, PostRepository.class,
    CommentRepository.class, ImageRepository.class, GroupRepository.class, ParticipantRepository.class,
    FavoriteGroupRepository.class, DistrictRepository.class, GroupAchievementRateRepository.class,
    ParticipantAchievementRateRepository.class
})
public @interface CustomDataJpaTest {

}
