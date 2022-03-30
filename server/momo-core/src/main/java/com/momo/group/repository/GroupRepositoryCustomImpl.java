package com.momo.group.repository;

import static com.momo.achievementrate.entity.QGroupAchievementRate.groupAchievementRate;
import static com.momo.favorite.entity.QFavoriteGroup.favoriteGroup;
import static com.momo.group.entity.QGroup.group;
import static com.momo.group.entity.QParticipant.participant;

import com.momo.district.entity.City;
import com.momo.group.dto.GroupCardResponse;
import com.momo.group.dto.GroupResponse;
import com.momo.group.dto.GroupSearchConditionRequest;
import com.momo.group.dto.QGroupCardResponse;
import com.momo.group.dto.QGroupResponse;
import com.momo.group.entity.Category;
import com.momo.group.entity.Group;
import com.momo.user.domain.User;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Group> findGroupAndAchievementRateByUser(User loginUser) {
        return queryFactory
            .selectFrom(group)
            .leftJoin(group.achievementRate, groupAchievementRate).fetchJoin()
            .where(
                group.isEnd.isFalse(),
                group.manager.eq(loginUser)
            )
            .fetch();
    }

    @Override
    public GroupResponse findDetailByGroupId(User loginUser, Long groupId) {
        return queryFactory
            .select(new QGroupResponse(
                group.id,
                group.manager.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.location,
                group.isOffline,
                group.introduction,
                group.recruitmentCnt,
                group.isEnd,
                JPAExpressions
                    .select(participant.count())
                    .from(participant)
                    .where(participant.group.eq(group)),
                JPAExpressions
                    .select(participant.count().eq(1L))
                    .from(participant)
                    .where(participant.group.id.eq(groupId).and(participant.user.eq(loginUser)))
            ))
            .from(group)
            .where(group.id.eq(groupId))
            .fetchOne();
    }

    @Override
    public List<GroupCardResponse> findAllBySearchConditionOrderByCreatedDateDesc(
        User loginUser, GroupSearchConditionRequest request, Pageable pageable
    ) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipant(),
                isFavoriteGroup(loginUser)
            ))
            .from(group)
            .where(
                group.name.contains(request.getGroupName()),
                group.isEnd.isFalse(),
                cityIn(request.getCities()),
                categoryIn(request.getCategories())
            )
            .orderBy(group.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    @Override
    public List<GroupCardResponse> findAllByCitiesAndCategoriesOrderByCreatedDateDesc(
        User loginUser, List<City> cities, List<Category> categories, Pageable pageable
    ) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipant(),
                isFavoriteGroup(loginUser)
            ))
            .from(group)
            .where(
                group.isEnd.isFalse(),
                cityIn(cities),
                categoryIn(categories)
            )
            .orderBy(group.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    private BooleanExpression cityIn(List<City> cities) {
        return CollectionUtils.isEmpty(cities) ? null : group.location.city.in(cities);
    }

    private BooleanExpression categoryIn(List<Category> categories) {
        return CollectionUtils.isEmpty(categories) ? null : group.category.in(categories);
    }

    @Override
    public List<GroupCardResponse> findByUniversityOrderByIdDesc(
        User loginUser, String university, Long lastGroupId, int size
    ) {
        return queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipant(),
                isFavoriteGroup(loginUser)
            ))
            .from(group)
            .where(
                group.isEnd.isFalse(),
                group.location.university.eq(university),
                ltLastGroupId(lastGroupId)
            )
            .orderBy(group.id.desc())
            .limit(size)
            .fetch();
    }

    @Override
    public List<GroupCardResponse> findByDistrictOrderByIdDesc(
        User loginUser, String district, Long lastGroupId, int size
    ) {
        return queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipant(),
                isFavoriteGroup(loginUser)
            ))
            .from(group)
            .where(
                group.isEnd.isFalse(),
                group.location.district.eq(district),
                ltLastGroupId(lastGroupId)
            )
            .orderBy(group.id.desc())
            .limit(size)
            .fetch();
    }

    @Override
    public List<GroupCardResponse> findByCategoriesOrderByIdDesc(
        User loginUser, List<Category> categories, Long lastGroupId, int size
    ) {
        return queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipant(),
                isFavoriteGroup(loginUser)
            ))
            .from(group)
            .where(
                group.isEnd.isFalse(),
                group.category.in(categories),
                ltLastGroupId(lastGroupId)
            )
            .orderBy(group.id.desc())
            .limit(size)
            .fetch();
    }

    private JPQLQuery<Long> countParticipant() {
        return JPAExpressions
            .select(participant.count())
            .from(participant)
            .where(participant.group.eq(group));
    }

    private JPQLQuery<Boolean> isFavoriteGroup(User user) {
        return JPAExpressions
            .select(favoriteGroup.isNotNull())
            .from(favoriteGroup)
            .where(
                favoriteGroup.group.eq(group),
                favoriteGroup.user.eq(user)
            );
    }

    private BooleanExpression ltLastGroupId(Long groupId) {
        if (Objects.isNull(groupId)) {
            return null;
        }
        return group.id.lt(groupId);
    }
}
