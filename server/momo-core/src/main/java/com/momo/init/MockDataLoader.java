package com.momo.init;

import static com.momo.Profile.DEVELOP;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile(DEVELOP)
@RequiredArgsConstructor
public class MockDataLoader implements CommandLineRunner {

    private static final int BATCH_SIZE = 100;
    private static final int NUMBER_OF_USERS = 200_000;
    private static final int NUMBER_OF_GROUPS = 200_000;
    private static final int NUMBER_OF_POSTS = 200_000;
    private static final int NUMBER_OF_SCHEDULES = 200_000;
    private static final int NUMBER_OF_COMMENTS = 200_000;

    private static final List<String> UNIVERSITIES = Arrays.asList("서울대학교", "연세대학교", "고려대학교");
    private static final List<String> DISTRICTS = Arrays.asList("강남구", "서초구", "송파구");
    private static final List<String> CATEGORIES = Arrays.asList("HEALTH", "RICE", "SELF_DEVELOPMENT", "LIFE", "HOBBY");
    private static final String CITY = "SEOUL";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        //insertMockData();
    }

    private void insertMockData() {
        insertMockUsers();
        insertMockGroups();
        insertMockPosts();
        insertMockSchedules();
        insertMockComments();
    }

    private void insertMockUsers() {
        for (int i = 0; i < NUMBER_OF_USERS / BATCH_SIZE; i++) {
            userBatchInsert(i);
        }
    }

    private void userBatchInsert(int batchCount) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO momo.user (`city`, `district`, `nickname`, `university`, `provider_id`, `created_date`, `last_modified_date`) VALUES (?, ?, ?, ?, ?, ?, ?)",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, CITY);
                    ps.setString(2, DISTRICTS.get(i % DISTRICTS.size()));
                    ps.setString(3, "유저" + (BATCH_SIZE * batchCount + i + 1));
                    ps.setString(4, UNIVERSITIES.get(i % UNIVERSITIES.size()));
                    ps.setString(5, String.valueOf(BATCH_SIZE * batchCount + i + 1));
                    ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                    ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                }

                @Override
                public int getBatchSize() {
                    return BATCH_SIZE;
                }
            }
        );
    }

    private void insertMockGroups() {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_GROUPS / BATCH_SIZE; i++) {
            groupBatchInsert(random, i);
        }
    }

    private void groupBatchInsert(Random random, int batchCount) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO momo.group_tb(`category`, `city`, `district`, `university`, `is_end`, `is_offline`, `name`, `introduction`, `recruitment_cnt`, `manager_id`, `start_date`, `created_date`, `last_modified_date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, CATEGORIES.get(i % CATEGORIES.size()));
                    ps.setString(2, CITY);
                    ps.setString(3, DISTRICTS.get(i % DISTRICTS.size()));
                    ps.setString(4, UNIVERSITIES.get(i % UNIVERSITIES.size()));
                    ps.setBoolean(5, false);
                    ps.setBoolean(6, i % 2 == 0);
                    ps.setString(7, "모임" + (BATCH_SIZE * batchCount + i + 1));
                    ps.setString(8, "모임 소개");
                    ps.setInt(9, 10);
                    ps.setLong(10, (long) BATCH_SIZE * batchCount + i + 1); //ID가 i인 유저는 ID가 i인 모임의 관리자
                    ps.setDate(11, Date.valueOf(getRandomDate(random)));
                    ps.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now()));
                    ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
                }

                @Override
                public int getBatchSize() {
                    return BATCH_SIZE;
                }
            }
        );
    }

    private LocalDate getRandomDate(Random random) {
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(27) + 1;
        return LocalDate.of(2021, month, day);
    }

    private void insertMockPosts() {
        for (int i = 0; i < NUMBER_OF_POSTS / BATCH_SIZE; i++) {
            postBatchInsert(i);
        }
    }

    private void postBatchInsert(int batchCount) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO momo.post(`contents`, `title`, `type`, `author_id`, `group_id`, `created_date`, `last_modified_date`) VALUES (?, ?, ?, ?, ?, ?, ?)",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, "게시글 내용");
                    ps.setString(2, "게시글 제목");
                    ps.setString(3, "NORMAL");
                    ps.setLong(4, (long) BATCH_SIZE * batchCount + i + 1);
                    ps.setLong(5, (long) BATCH_SIZE * batchCount + i + 1);
                    ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                    ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                }

                @Override
                public int getBatchSize() {
                    return BATCH_SIZE;
                }
            }
        );
    }

    private void insertMockSchedules() {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_SCHEDULES / BATCH_SIZE; i++) {
            scheduleBatchInsert(random, i);
        }
    }

    private void scheduleBatchInsert(Random random, int batchCount) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO momo.schedule(attendance_check, title, contents, is_offline, author_id, group_id, start_date_time, created_date, last_modified_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setBoolean(1, false);
                    ps.setString(2, "일정 제목");
                    ps.setString(3, "일정 내용");
                    ps.setBoolean(4, i % 2 == 0);
                    ps.setLong(5, (long) BATCH_SIZE * batchCount + i + 1);
                    ps.setLong(6, (long) BATCH_SIZE * batchCount + i + 1);
                    ps.setTimestamp(7, Timestamp.valueOf(getRandomDateTime(random)));
                    ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                    ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
                }

                @Override
                public int getBatchSize() {
                    return BATCH_SIZE;
                }
            }
        );
    }

    private LocalDateTime getRandomDateTime(Random random) {
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(27) + 1;
        int hour = random.nextInt(23);
        int minute = random.nextInt(60);
        return LocalDateTime.of(2021, month, day, hour, minute);
    }

    private void insertMockComments() {
        for (int i = 0; i < NUMBER_OF_COMMENTS / BATCH_SIZE; i++) {
            commentBatchInsert(i);
        }
    }

    private void commentBatchInsert(int batchCount) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO momo.comment(contents, post_id, user_id, created_date, last_modified_date) VALUES (?, ?, ?, ?, ?)",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, "댓글 내용");
                    ps.setLong(2, (long) BATCH_SIZE * batchCount + i + 1);
                    ps.setLong(3, (long) BATCH_SIZE * batchCount + i + 1);
                    ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                    ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                }

                @Override
                public int getBatchSize() {
                    return BATCH_SIZE;
                }
            }
        );
    }
}
