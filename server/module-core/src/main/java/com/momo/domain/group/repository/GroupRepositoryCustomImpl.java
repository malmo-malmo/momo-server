package com.momo.domain.group.repository;

import static com.momo.domain.group.entity.QGroups.groups;
import static com.momo.domain.group.entity.QParticipant.participant;

import com.momo.domain.group.entity.Category;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.QGroupCardResponse;
import com.momo.domain.group.dto.QGroupResponse;
import com.momo.domain.user.entity.User;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public GroupResponse findGroupAndParticipantCntAndAuthorityById(User loginUser, Long groupId) {
        return queryFactory
            .select(new QGroupResponse(
                groups.id,
                groups.manager.id,
                groups.name,
                groups.imageUrl,
                groups.startDate,
                groups.university,
                groups.city,
                groups.district,
                groups.isOffline,
                groups.introduction,
                groups.recruitmentCnt,
                groups.isEnd,
                JPAExpressions
                    .select(participant.count())
                    .from(participant)
                    .where(participant.group.eq(groups)),
                JPAExpressions
                    .select(participant.count().eq(1L))
                    .from(participant)
                    .where(participant.group.id.eq(groupId).and(participant.user.eq(loginUser)))
            ))
            .from(groups)
            .where(groups.id.eq(groupId))
            .fetchOne();
    }

    @Override
    public List<GroupCardResponse> findAllBySearchConditionOrderByCreatedDateDesc(List<String> cities,
                                                                                  List<Category> categories, Pageable pageable) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                groups.id,
                groups.name,
                groups.imageUrl,
                groups.startDate,
                groups.isOffline,
                countParticipantQuery()
            ))
            .from(groups)
            .where(
                cityIn(cities),
                categoryIn(categories)
            )
            .orderBy(groups.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    private BooleanExpression cityIn(List<String> cities) {
        return CollectionUtils.isEmpty(cities) ? null : groups.city.in(cities);
    }

    private BooleanExpression categoryIn(List<Category> categories) {
        return CollectionUtils.isEmpty(categories) ? null : groups.category.in(categories);
    }

    @Override
    public List<GroupCardResponse> findAllByUniversityOrderByCreatedDateDesc(String university, Pageable pageable) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                groups.id,
                groups.name,
                groups.imageUrl,
                groups.startDate,
                groups.isOffline,
                countParticipantQuery()
            ))
            .from(groups)
            .where(groups.university.eq(university))
            .orderBy(groups.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    @Override
    public List<GroupCardResponse> findAllByDistrictOrderByCreatedDateDesc(String district, Pageable pageable) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                groups.id,
                groups.name,
                groups.imageUrl,
                groups.startDate,
                groups.isOffline,
                countParticipantQuery()
            ))
            .from(groups)
            .where(groups.district.eq(district))
            .orderBy(groups.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    @Override
    public List<GroupCardResponse> findAllByCategoriesOrderByCreatedDateDesc(List<Category> categories,
        Pageable pageable) {
        QueryResults<GroupCardResponse> results = queryFactory
            .select(new QGroupCardResponse(
                groups.id,
                groups.name,
                groups.imageUrl,
                groups.startDate,
                groups.isOffline,
                countParticipantQuery()
            ))
            .from(groups)
            .where(groups.category.in(categories))
            .orderBy(groups.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    private JPQLQuery<Long> countParticipantQuery() {
        return JPAExpressions
            .select(participant.count())
            .from(participant)
            .where(participant.group.eq(groups));
    }
}
