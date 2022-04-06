package com.momo.group;

import com.momo.auth.CurrentUser;
import com.momo.group.application.dto.request.ParticipantRequest;
import com.momo.group.application.dto.response.ParticipantResponse;
import com.momo.user.domain.User;
import java.net.URI;
import java.net.URISyntaxException;
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
    @PostMapping("/group/apply-participant")
    public ResponseEntity<Void> applyParticipantByGroup(@CurrentUser User user,
        @RequestBody ParticipantRequest request) throws URISyntaxException {
        Long participantId = participantService.applyParticipantByGroup(user, request.getGroupId());
        return ResponseEntity.created(new URI("/api/group/apply-participant/" + participantId)).build();
    }

    @GetMapping("/group/participants")
    public ResponseEntity<List<ParticipantResponse>> findByGroup(@CurrentUser User user,
        @RequestParam Long groupId) {
        List<ParticipantResponse> responses = participantService.findByGroupId(user, groupId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/group/{groupId}/participant")
    public ResponseEntity<Void> withdraw(@CurrentUser User user, @PathVariable Long groupId) {
        participantService.withdrawByGroupId(user, groupId);
        return ResponseEntity.noContent().build();
    }
}
