package com.momo.domain.group.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GroupSearchEngine extends ElasticsearchRepository<GroupSearch, Long>, CustomGroupSearchEngine {

}
