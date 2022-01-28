package com.momo.domain.management.dto;

import com.momo.domain.group.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SummaryParticipationGroupResponse {

    private Long id;
    private String name;
    private Category category;
}
