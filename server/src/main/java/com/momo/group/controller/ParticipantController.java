package com.momo.group.controller;

import com.momo.group.controller.dto.ParticipantResponse;
import com.momo.group.service.ParticipantService;
import com.momo.security.CurrentUser;
import com.momo.user.domain.model.User;
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

    @GetMapping("/group/participants")
    public ResponseEntity<List<ParticipantResponse>> findParticipantsByGroup(@CurrentUser User user,
        @RequestParam Long groupId) {
        List<ParticipantResponse> responses = participantService.findParticipantsByGroup(user, groupId);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/group/apply-participant")
    public ResponseEntity<Void> applyParticipantByGroup(@CurrentUser User user,
        @RequestBody Long groupId) {
        participantService.applyParticipantByGroup(user, groupId);
        return ResponseEntity.ok().build();
    }
}
