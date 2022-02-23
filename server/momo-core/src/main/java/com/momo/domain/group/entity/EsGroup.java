package com.momo.domain.group.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Getter
@ToString
@Document(indexName = "momo_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EsGroup {

    @Id
    private Long id;

    private String name;

    @PersistenceConstructor
    public EsGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
