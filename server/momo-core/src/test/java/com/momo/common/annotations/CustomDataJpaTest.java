package com.momo.common.annotations;

import static com.momo.Profile.TEST;

import com.momo.achievementrate.repository.GroupAchievementRateRepository;
import com.momo.achievementrate.repository.ParticipantAchievementRateRepository;
import com.momo.district.repository.DistrictRepository;
import com.momo.favorite.repository.FavoriteGroupRepository;
import com.momo.group.repository.GroupRepository;
import com.momo.group.repository.ParticipantRepository;
import com.momo.post.repository.CommentRepository;
import com.momo.post.repository.ImageRepository;
import com.momo.post.repository.PostRepository;
import com.momo.schedule.repository.AttendanceRepository;
import com.momo.schedule.repository.ScheduleRepository;
import com.momo.user.repository.UserRepository;
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
