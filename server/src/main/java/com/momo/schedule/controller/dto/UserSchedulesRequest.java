package com.momo.schedule.controller.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSchedulesRequest {

    @NotNull(message = "날짜는 필수값입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate searchStartDate;

    @NotNull(message = "날짜는 필수값입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate searchEndDate;
}
