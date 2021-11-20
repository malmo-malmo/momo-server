package com.momo.group.domain.repository;

import static com.momo.group.domain.model.QGroups.groups;

import com.momo.group.domain.model.Category;
import com.momo.group.domain.model.Groups;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Groups> findAllByCategoriesOrderByCreatedDateDesc(List<Category> categories, Pageable pageable) {
        BooleanBuilder whereClause = new BooleanBuilder();
        for (Category category : categories) {
            whereClause.or(groups.category.eq(category));
        }
        QueryResults<Groups> results = queryFactory
            .selectFrom(groups)
            .where(whereClause)
            .orderBy(groups.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }

    @Override
    public List<Groups> findAllBySearchConditionOrderByCreatedDateDesc(List<String> cities, List<Category> categories,
        Pageable pageable) {
        //TODO : 뭔가 개선할 수 있을 듯.... where 절에 or-> in 으로 바꾸기
        BooleanBuilder cityWhereClause = new BooleanBuilder();
        BooleanBuilder categoryWhereClause = new BooleanBuilder();
        for (String city : cities) {
            cityWhereClause.or(groups.city.eq(city));
        }
        for (Category category : categories) {
            categoryWhereClause.or(groups.category.eq(category));
        }
        QueryResults<Groups> results = queryFactory
            .selectFrom(groups)
            .where(cityWhereClause.and(categoryWhereClause))
            .orderBy(groups.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal()).getContent();
    }
}