package com.momo.group.domain.repository;

import com.momo.group.controller.dto.ParticipantResponse;
import com.momo.group.domain.model.Groups;
import java.util.List;

public interface ParticipantRepositoryCustom {

    List<ParticipantResponse> findParticipantAndAttendanceRateByGroup(Groups group, Long scheduleCnt);
}
