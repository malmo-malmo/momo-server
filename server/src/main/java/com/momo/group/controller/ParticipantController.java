package com.momo.group.controller;

import com.momo.group.controller.dto.ParticipantResponse;
import com.momo.group.service.ParticipantService;
import com.momo.auth.domain.CurrentUser;
import com.momo.user.domain.model.User;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @ApiOperation(value = "모임 참여자 목록 조회")
    @GetMapping("/group/participants")
    public ResponseEntity<List<ParticipantResponse>> findParticipantsByGroup(@CurrentUser User user,
        @RequestParam Long groupId) {
        List<ParticipantResponse> responses = participantService.findParticipantsByGroup(user, groupId);
        return ResponseEntity.ok(responses);
    }

    /*
    테스트를 위해 임시로 만든 API
    */
    @ApiOperation(value = "참여 신청")
    @PostMapping("/group/apply-participant")
    public ResponseEntity<Void> applyParticipantByGroup(@CurrentUser User user,
        @RequestBody Long groupId) {
        participantService.applyParticipantByGroup(user, groupId);
        return ResponseEntity.ok().build();
    }
}
