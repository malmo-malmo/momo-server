package com.momo.domain.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipatingGroupCountResponse {

    private Long count;

    public static ParticipatingGroupCountResponse of(Long count) {
        return new ParticipatingGroupCountResponse(count);
    }
}
