package com.momo.schedule.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupSchedulesRequest {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @NotNull(message = "페이지는 필수값입니다.")
    private Integer page;

    @NotNull(message = "사이즈는 필수값입니다.")
    private Integer size;
}
