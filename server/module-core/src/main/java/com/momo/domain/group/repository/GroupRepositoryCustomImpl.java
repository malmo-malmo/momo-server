package com.momo.domain.group.repository;

import static com.momo.domain.group.entity.QGroup.group;
import static com.momo.domain.group.entity.QParticipant.participant;
import static com.momo.domain.user.entity.QFavoriteGroup.favoriteGroup;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.dto.QGroupCardResponse;
import com.momo.domain.group.dto.QGroupResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.entity.User;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public GroupResponse findGroupAndParticipantCntAndAuthorityById(User loginUser, Long groupId) {
        return queryFactory
            .select(new QGroupResponse(
                group.id,
                group.manager.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.university,
                group.city,
                group.district,
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
    public List<GroupCardResponse> findAllBySearchConditionOrderByCreatedDateDesc(User loginUser,
        GroupSearchConditionRequest request, Pageable pageable) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipantQuery(),
                isFavoriteGroupQuery(loginUser)
            ))
            .from(group)
            .where(
                cityIn(request.getCities()),
                categoryIn(request.getCategories())
            )
            .orderBy(group.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    private BooleanExpression cityIn(List<City> cities) {
        return CollectionUtils.isEmpty(cities) ? null : group.city.in(cities);
    }

    private BooleanExpression categoryIn(List<Category> categories) {
        return CollectionUtils.isEmpty(categories) ? null : group.category.in(categories);
    }

    @Override
    public List<GroupCardResponse> findAllByUniversityOrderByCreatedDateDesc(User loginUser, String university,
        Pageable pageable) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipantQuery(),
                isFavoriteGroupQuery(loginUser)
            ))
            .from(group)
            .where(group.university.eq(university))
            .orderBy(group.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    @Override
    public List<GroupCardResponse> findAllByDistrictOrderByCreatedDateDesc(User loginUser, String district,
        Pageable pageable) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipantQuery(),
                isFavoriteGroupQuery(loginUser)
            ))
            .from(group)
            .where(group.district.eq(district))
            .orderBy(group.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    @Override
    public List<GroupCardResponse> findAllByCategoriesOrderByCreatedDateDesc(User loginUser, List<Category> categories,
        Pageable pageable) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipantQuery(),
                isFavoriteGroupQuery(loginUser)
            ))
            .from(group)
            .where(group.category.in(categories))
            .orderBy(group.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    private JPQLQuery<Long> countParticipantQuery() {
        return JPAExpressions
            .select(participant.count())
            .from(participant)
            .where(participant.group.eq(group));
    }

    private JPQLQuery<Boolean> isFavoriteGroupQuery(User loginUser) {
        return JPAExpressions
            .select(favoriteGroup.isNotNull())
            .from(favoriteGroup)
            .where(
                favoriteGroup.group.eq(group),
                favoriteGroup.user.eq(loginUser)
            );
    }
}
