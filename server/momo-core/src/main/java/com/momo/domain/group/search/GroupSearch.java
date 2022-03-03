package com.momo.domain.group.search;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Entity
@Document(indexName = "momo_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupSearch {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private Category category;

    @Field(type = FieldType.Text)
    private City city;

    @Field(type = FieldType.Date)
    private LocalDateTime createdDate;

    @PersistenceConstructor
    public GroupSearch(Long id, String name, Category category, City city, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.city = city;
        this.createdDate = createdDate;
    }
}
