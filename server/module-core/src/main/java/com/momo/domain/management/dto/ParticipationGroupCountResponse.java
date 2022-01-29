package com.momo.domain.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationGroupCountResponse {

    private Long count;

    public static ParticipationGroupCountResponse of(Long count) {
        return new ParticipationGroupCountResponse(count);
    }
}
