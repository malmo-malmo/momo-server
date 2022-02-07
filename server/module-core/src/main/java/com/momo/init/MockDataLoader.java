package com.momo.init;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.user.entity.Location;
import com.momo.domain.user.entity.Social;
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
@Profile("dev")
@RequiredArgsConstructor
public class MockDataLoader implements CommandLineRunner {

    private static final int MAX_GROUP_COUNT = 50;
    private static final int MAX_USER_COUNT = 500;
    private static final int MAX_POST_COUNT = 30;
    private static final int MAX_SCHEDULE_COUNT = 30;
    private static final List<String> UNIVERSITIES =
        Arrays.asList("서울대학교", "연세대학교", "고려대학교", "서강대학교", "성균관대학교", "한양대학교");
    private static final List<String> DISTRICTS =
        Arrays.asList("강남구", "강동구", "은평구", "서초구", "송파구", "종로구", "마포구");
    private static final List<City> CITIES = List.of(City.SEOUL);

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;
    private final PostRepository postRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public void run(String... args) {
        long savedUserCount = userRepository.count();
        if (savedUserCount > 0) {
            return;
        }
        insertTestData();
    }

    @Transactional
    public void insertTestData() {
        List<User> savedManagers = insertManager();
        List<Group> savedGroups = insertGroup(savedManagers);
        List<User> savedUsers = insertUser();
        insertParticipant(savedGroups, savedUsers);
        insertPost(savedGroups);
        insertSchedule(savedGroups);
    }

    public List<User> insertManager() {
        List<User> managers = new ArrayList<>();
        for (int count = 1; count <= MAX_GROUP_COUNT; count++) {
            Collections.shuffle(UNIVERSITIES);
            Collections.shuffle(DISTRICTS);
            User manager = User.builder()
                .loginInfo(Social.from(SocialProvider.KAKAO, String.valueOf(count)))
                .nickname("모임장" + count)
                .location(Location.builder()
                    .university(UNIVERSITIES.get(0))
                    .city(CITIES.get(0))
                    .district(DISTRICTS.get(0))
                    .build())
                .build();
            managers.add(manager);
        }
        return userRepository.saveAll(managers);
    }

    public List<Group> insertGroup(List<User> managers) {
        Random random = new Random();
        List<Group> groups = new ArrayList<>();
        List<Category> categories = Stream.of(Category.values()).collect(Collectors.toList());
        for (int count = 1; count <= MAX_GROUP_COUNT; count++) {
            Collections.shuffle(categories);
            Collections.shuffle(UNIVERSITIES);
            Collections.shuffle(DISTRICTS);
            Group group = Group.builder()
                .manager(managers.get(count - 1))
                .name("모임" + count)
                .category(categories.get(0))
                .startDate(createRandomDate())
                .location(Location.builder()
                    .university(UNIVERSITIES.get(0))
                    .city(CITIES.get(0))
                    .district(DISTRICTS.get(0))
                    .build())
                .introduction("모임 설명")
                .recruitmentCnt(random.nextInt(100) + 10)
                .isOffline(count % 2 == 0)
                .isEnd(false)
                .build();
            groups.add(group);
        }
        return groupRepository.saveAll(groups);
    }

    public LocalDate createRandomDate() {
        Random random = new Random();
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(27) + 1;
        return LocalDate.of(2021, month, day);
    }

    public List<User> insertUser() {
        List<User> users = new ArrayList<>();
        for (int count = 1; count <= MAX_USER_COUNT; count++) {
            Collections.shuffle(UNIVERSITIES);
            Collections.shuffle(DISTRICTS);
            User user = User.builder()
                .loginInfo(Social.from(SocialProvider.KAKAO, String.valueOf(MAX_GROUP_COUNT + count)))
                .nickname("참여자" + count)
                .location(Location.builder()
                    .university(UNIVERSITIES.get(0))
                    .city(CITIES.get(0))
                    .district(DISTRICTS.get(0))
                    .build())
                .build();
            users.add(user);
        }
        return userRepository.saveAll(users);
    }

    public void insertParticipant(List<Group> groups, List<User> users) {
        List<Participant> participants = new ArrayList<>();
        int userCount = 0;
        for (Group group : groups) {
            Participant manager = Participant.builder()
                .user(group.getManager())
                .group(group)
                .build();
            participants.add(manager);

            for (int count = userCount; count < userCount + 5; count++) {
                Participant participant = Participant.builder()
                    .user(users.get(count))
                    .group(group)
                    .build();
                participants.add(participant);
            }
            userCount += 5;
        }
        participantRepository.saveAll(participants);
    }

    public void insertPost(List<Group> groups) {
        List<Post> posts = new ArrayList<>();
        for (Group group : groups) {
            for (int count = 1; count <= MAX_POST_COUNT; count++) {
                Post post = Post.builder()
                    .author(group.getManager())
                    .group(group)
                    .title("게시물" + count)
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
            for (int count = 1; count <= MAX_SCHEDULE_COUNT; count++) {
                Schedule schedule = Schedule.builder()
                    .author(group.getManager())
                    .group(group)
                    .title("일정" + count)
                    .contents("일정 내용")
                    .isOffline(count % 2 == 0)
                    .startDateTime(createRandomDateTime())
                    .build();
                schedules.add(schedule);
            }
        }
        scheduleRepository.saveAll(schedules);
    }

    public LocalDateTime createRandomDateTime() {
        Random random = new Random();
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(27) + 1;
        int hour = random.nextInt(23);
        int minute = random.nextInt(60);
        return LocalDateTime.of(2021, month, day, hour, minute);
    }
}
