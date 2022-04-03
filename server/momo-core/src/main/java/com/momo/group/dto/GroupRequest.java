package com.momo.group.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequest {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;
}
