package com.momo.group.domain.repository;

import com.momo.group.domain.search.GroupSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GroupSearchEngine extends ElasticsearchRepository<GroupSearch, Long>, CustomGroupSearchEngine {

}
