package com.momo.api.management;

import com.momo.common.CurrentUser;
import com.momo.domain.management.dto.ManagingGroupCardResponse;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCountResponse;
import com.momo.domain.management.dto.SummaryParticipationGroupResponse;
import com.momo.domain.management.service.ManagementService;
import com.momo.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/management")
public class ManagementController {

    private final ManagementService managementService;

    @GetMapping("/participating-group/count")
    public ResponseEntity<ParticipatingGroupCountResponse> findParticipatingGroupCount(@CurrentUser User user) {
        ParticipatingGroupCountResponse response = managementService.findParticipatingGroupCountByUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/participating-groups/details")
    public ResponseEntity<List<ParticipatingGroupCardResponse>> findParticipatingGroups(@CurrentUser User user) {
        List<ParticipatingGroupCardResponse> responses = managementService.findParticipatingGroupsByUser(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/participating-groups/summary")
    public ResponseEntity<List<SummaryParticipationGroupResponse>> findSummaryParticipationGroups(
        @CurrentUser User user) {
        return ResponseEntity.ok(managementService.findSummaryParticipationGroupsByUser(user));
    }

    @GetMapping("/managing-groups")
    public ResponseEntity<List<ManagingGroupCardResponse>> findManagingGroups(@CurrentUser User user) {
        List<ManagingGroupCardResponse> responses = managementService.findManagingGroupsByUser(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<MyPostCardResponse>> findMyPosts(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<MyPostCardResponse> responses = managementService.findMyPostsByUser(user, page, size);
        return ResponseEntity.ok(responses);
    }
}
