package com.momo.init;

import static com.momo.Profile.DEVELOP;
import static com.momo.domain.district.entity.City.SEOUL;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.user.entity.Location;
import com.momo.domain.user.entity.LoginInfo;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile(DEVELOP)
@RequiredArgsConstructor
public class MockDataLoader implements CommandLineRunner {

    /**
     * 유저 수 : 100, 모임 수 : 5만, 게시물 + 공지사항 수 : 10만, 일정 수 : 10만
     */

    private static final int NUMBER_OF_USERS = 100;
    private static final int NUMBER_OF_GROUPS_PER_USER = 500;
    private static final int NUMBER_OF_POSTS_PER_GROUP = 2;
    private static final int NUMBER_OF_SCHEDULES_PER_GROUP = 2;

    private static final List<String> UNIVERSITIES = Arrays.asList("서울대학교", "연세대학교", "고려대학교");
    private static final List<String> DISTRICTS = Arrays.asList("강남구", "서초구", "송파구");
    private static final List<City> CITIES = List.of(SEOUL);

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final PostRepository postRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0 || groupRepository.count() > 0 ||
            postRepository.count() > 0 || scheduleRepository.count() > 0) {
            return;
        }
        insertTestData();
    }

    @Transactional
    public void insertTestData() {
        List<User> users = saveUsers();
        List<Group> groups = saveGroups(users);
        savePosts(groups);
        insertSchedule(groups);
    }

    public List<User> saveUsers() {
        List<User> users = new ArrayList<>();
        for (int count = 1; count <= NUMBER_OF_USERS; count++) {
            Collections.shuffle(UNIVERSITIES);
            Collections.shuffle(DISTRICTS);
            User manager = User.builder()
                .loginInfo(LoginInfo.createEmptyRefreshToken(SocialProvider.KAKAO, String.valueOf(count)))
                .nickname("테스트 유저" + count)
                .location(Location.builder()
                    .university(UNIVERSITIES.get(0))
                    .city(CITIES.get(0))
                    .district(DISTRICTS.get(0))
                    .build())
                .build();
            users.add(manager);
        }
        return userRepository.saveAll(users);
    }

    public List<Group> saveGroups(List<User> users) {
        List<Group> groups = new ArrayList<>();
        List<Category> categories = Stream.of(Category.values()).collect(Collectors.toList());

        int groupIdx = 1;
        for (int i = 1; i <= NUMBER_OF_USERS; i++) {
            for (int j = 1; j <= NUMBER_OF_GROUPS_PER_USER; j++) {
                Collections.shuffle(categories);
                Collections.shuffle(UNIVERSITIES);
                Collections.shuffle(DISTRICTS);
                Group group = Group.builder()
                    .manager(users.get(i - 1))
                    .name("테스트 모임" + groupIdx++)
                    .category(categories.get(0))
                    .startDate(getRandomDate())
                    .location(Location.builder()
                        .university(UNIVERSITIES.get(0))
                        .city(CITIES.get(0))
                        .district(DISTRICTS.get(0))
                        .build())
                    .introduction("모임 설명")
                    .recruitmentCnt(20)
                    .isOffline(j % 2 == 0)
                    .isEnd(false)
                    .build();
                groups.add(group);
            }
        }
        return groupRepository.saveAll(groups);
    }

    public LocalDate getRandomDate() {
        Random random = new Random();
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(27) + 1;
        return LocalDate.of(2021, month, day);
    }

    public void savePosts(List<Group> groups) {
        List<Post> posts = new ArrayList<>();
        for (Group group : groups) {
            for (int count = 1; count <= NUMBER_OF_POSTS_PER_GROUP; count++) {
                Post post = Post.builder()
                    .author(group.getManager())
                    .group(group)
                    .title(group.getName() + "의 게시물" + count)
                    .contents("게시물 내용")
                    .type(count % 2 == 0 ? PostType.NOTICE : PostType.NORMAL)
                    .build();
                posts.add(post);
            }
        }
        postRepository.saveAll(posts);
    }

    public void insertSchedule(List<Group> groups) {
        List<Schedule> schedules = new ArrayList<>();
        for (Group group : groups) {
            for (int count = 1; count <= NUMBER_OF_SCHEDULES_PER_GROUP; count++) {
                Schedule schedule = Schedule.builder()
                    .author(group.getManager())
                    .group(group)
                    .title(group.getName() + "의 일정" + count)
                    .contents("일정 내용")
                    .isOffline(count % 2 == 0)
                    .startDateTime(getRandomDateTime())
                    .build();
                schedules.add(schedule);
            }
        }
        scheduleRepository.saveAll(schedules);
    }

    public LocalDateTime getRandomDateTime() {
        Random random = new Random();
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(27) + 1;
        int hour = random.nextInt(23);
        int minute = random.nextInt(60);
        return LocalDateTime.of(2021, month, day, hour, minute);
    }
}
