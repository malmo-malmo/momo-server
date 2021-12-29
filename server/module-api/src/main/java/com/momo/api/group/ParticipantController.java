package com.momo.api.group;

import com.momo.domain.auth.domain.CurrentUser;
import com.momo.domain.group.dto.ParticipantResponse;
import com.momo.domain.group.service.ParticipantService;
import com.momo.domain.user.domain.model.User;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @ApiOperation(value = "모임 참여자 목록 조회")
    @GetMapping("/group/participants")
    public ResponseEntity<List<ParticipantResponse>> findByGroup(@CurrentUser User user,
        @RequestParam Long groupId) {
        List<ParticipantResponse> responses = participantService.findByGroupId(user, groupId);
        return ResponseEntity.ok(responses);
    }

    @ApiOperation(value = "모임 탈퇴")
    @DeleteMapping("/group/{groupId}/participant")
    public ResponseEntity<Void> withdraw(@CurrentUser User user, @PathVariable Long groupId) {
        participantService.withdrawByGroupId(user, groupId);
        return ResponseEntity.noContent().build();
    }
}
