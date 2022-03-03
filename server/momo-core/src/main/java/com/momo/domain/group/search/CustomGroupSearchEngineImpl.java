package com.momo.domain.group.search;

import static com.momo.domain.favorite.entity.QFavoriteGroup.favoriteGroup;
import static com.momo.domain.group.entity.QGroup.group;
import static com.momo.domain.group.entity.QParticipant.participant;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.QGroupCardResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.entity.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@RequiredArgsConstructor
public class CustomGroupSearchEngineImpl implements CustomGroupSearchEngine {

    private static final String PARAM_SEPARATOR = " ";

    private final ElasticsearchOperations elasticsearchOperations;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupCardResponse> searchByNameLikeAndCitiesAndCategories(String name, List<City> cities,
        List<Category> categories, User user, Pageable pageable) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders
                .boolQuery()
                .must(QueryBuilders.matchQuery("name", name))
                .filter(QueryBuilders.matchQuery("city", toCityParam(cities)))
                .filter(QueryBuilders.matchQuery("category", toCategoryParam(categories))))
            .withSort(SortBuilders.fieldSort("created_date").order(SortOrder.DESC))
            .withPageable(pageable)
            .build();

        SearchHits<GroupSearch> search = elasticsearchOperations.search(query, GroupSearch.class);
        List<Long> ids = search.stream()
            .map(SearchHit::getContent)
            .map(GroupSearch::getId)
            .collect(Collectors.toList());

        return findAllByIds(ids, user);
    }

    private String toCityParam(List<City> cities) {
        return cities.stream()
            .map(City::getCode)
            .collect(Collectors.joining(PARAM_SEPARATOR));
    }

    private String toCategoryParam(List<Category> categories) {
        return categories.stream()
            .map(Category::getCode)
            .collect(Collectors.joining(PARAM_SEPARATOR));
    }

    private List<GroupCardResponse> findAllByIds(List<Long> ids, User user) {
        return queryFactory
            .select(new QGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                countParticipant(),
                isFavoriteGroupByUser(user)
            ))
            .from(group)
            .where(group.id.in(ids))
            .orderBy(group.createdDate.desc())
            .fetch();
    }

    private JPQLQuery<Long> countParticipant() {
        return JPAExpressions
            .select(participant.count())
            .from(participant)
            .where(participant.group.eq(group));
    }

    private JPQLQuery<Boolean> isFavoriteGroupByUser(User user) {
        return JPAExpressions
            .select(favoriteGroup.isNotNull())
            .from(favoriteGroup)
            .where(
                favoriteGroup.group.eq(group),
                favoriteGroup.user.eq(user)
            );
    }
}
