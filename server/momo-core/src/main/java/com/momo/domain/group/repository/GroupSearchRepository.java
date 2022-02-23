package com.momo.domain.group.repository;

import com.momo.domain.group.entity.EsGroup;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GroupSearchRepository extends ElasticsearchRepository<EsGroup, Long> {

    List<EsGroup> findAllByNameContains(String name);
}
