package com.momo;

import com.momo.group.domain.model.Category;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.model.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostType;
import com.momo.post.domain.repository.PostRepository;
import com.momo.schedule.domain.model.Schedule;
import com.momo.schedule.domain.repository.ScheduleRepository;
import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
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
@Profile("local")
@RequiredArgsConstructor
public class MockDataLoader implements CommandLineRunner {

    private static final int MAX_GROUP_COUNT = 50;
    private static final int MAX_USER_COUNT = 500;
    private static final int MAX_POST_COUNT = 30;
    private static final int MAX_SCHEDULE_COUNT = 30;
    private static final List<String> UNIVERSITIES = Arrays.asList("대학교1", "대학교2", "대학교3", "대학교4", "대학교5");
    private static final List<String> CITIES = Arrays.asList("서울", "경기", "인천", "부산");
    private static final List<String> DISTRICTS = Arrays.asList("강남구", "강동구", "은평구", "서초구");

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;
    private final PostRepository postRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public void run(String... args) {
        insertTestData();
    }

    @Transactional
    public void insertTestData() {
        List<User> savedManagers = insertManager();
        List<Groups> savedGroups = insertGroup(savedManagers);
        List<User> savedUsers = insertUser();
        insertParticipant(savedGroups, savedUsers);
        insertPost(savedGroups);
        insertSchedule(savedGroups);
    }

    public List<User> insertManager() {
        List<User> managers = new ArrayList<>();
        for (int count = 1; count <= MAX_GROUP_COUNT; count++) {
            Collections.shuffle(UNIVERSITIES);
            Collections.shuffle(CITIES);
            Collections.shuffle(DISTRICTS);
            User manager = User.builder()
                .providerId(String.valueOf(count))
                .provider(SocialProvider.KAKAO)
                .nickname("모임장" + count)
                .university(UNIVERSITIES.get(0))
                .city(CITIES.get(0))
                .district(DISTRICTS.get(0))
                .build();
            managers.add(manager);
        }
        return userRepository.saveAll(managers);
    }

    public List<Groups> insertGroup(List<User> managers) {
        Random random = new Random();
        List<Groups> groups = new ArrayList<>();
        List<Category> categories = Stream.of(Category.values()).collect(Collectors.toList());
        for (int count = 1; count <= MAX_GROUP_COUNT; count++) {
            Collections.shuffle(categories);
            Collections.shuffle(UNIVERSITIES);
            Collections.shuffle(CITIES);
            Collections.shuffle(DISTRICTS);
            Groups group = Groups.builder()
                .manager(managers.get(count - 1))
                .name("모임" + count)
                .category(categories.get(0))
                .startDate(LocalDate.now())
                .university(UNIVERSITIES.get(0))
                .city(CITIES.get(0))
                .district(DISTRICTS.get(0))
                .introduction("모임 설명")
                .recruitmentCnt(random.nextInt(100) + 10)
                .isOffline(count % 2 == 0)
                .isEnd(false)
                .build();
            groups.add(group);
        }
        return groupRepository.saveAll(groups);
    }

    public List<User> insertUser() {
        List<User> users = new ArrayList<>();
        for (int count = 1; count <= MAX_USER_COUNT; count++) {
            Collections.shuffle(UNIVERSITIES);
            Collections.shuffle(CITIES);
            Collections.shuffle(DISTRICTS);
            User user = User.builder()
                .providerId(String.valueOf(MAX_GROUP_COUNT + count))
                .provider(SocialProvider.KAKAO)
                .nickname("참여자" + count)
                .university(UNIVERSITIES.get(0))
                .city(CITIES.get(0))
                .district(DISTRICTS.get(0))
                .build();
            users.add(user);
        }
        return userRepository.saveAll(users);
    }

    public void insertParticipant(List<Groups> groups, List<User> users) {
        List<Participant> participants = new ArrayList<>();
        int userCount = 0;
        for (Groups group : groups) {
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

    public void insertPost(List<Groups> groups) {
        List<Post> posts = new ArrayList<>();
        for (Groups group : groups) {
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

    public void insertSchedule(List<Groups> groups) {
        List<Schedule> schedules = new ArrayList<>();
        Random random = new Random();
        int year = 2021;
        for (Groups group : groups) {
            for (int count = 1; count <= MAX_SCHEDULE_COUNT; count++) {
                int month = random.nextInt(11) + 1;
                int day = random.nextInt(27) + 1;
                int hour = random.nextInt(23);
                int minute = random.nextInt(60);
                Schedule schedule = Schedule.builder()
                    .author(group.getManager())
                    .group(group)
                    .title("일정" + count)
                    .contents("일정 내용")
                    .isOffline(count % 2 == 0)
                    .startDateTime(LocalDateTime.of(year, month, day, hour, minute))
                    .build();
                schedules.add(schedule);
            }
        }
        scheduleRepository.saveAll(schedules);
    }
}
