package com.momo.management;

import com.momo.auth.CurrentUser;
import com.momo.management.dto.MyGroupCardResponse;
import com.momo.management.dto.MyGroupSummaryResponse;
import com.momo.management.dto.MyPostCardResponse;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.management.dto.ParticipationGroupCountResponse;
import com.momo.management.dto.ParticipationGroupSummaryResponse;
import com.momo.management.service.ManagementService;
import com.momo.user.entity.User;
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

    @GetMapping("/participation-group/count")
    public ResponseEntity<ParticipationGroupCountResponse> findParticipationGroupCount(
        @CurrentUser User user
    ) {
        ParticipationGroupCountResponse response = managementService.findParticipationGroupCountByUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/participation-groups/details")
    public ResponseEntity<List<ParticipationGroupCardResponse>> findParticipationGroups(
        @CurrentUser User user
    ) {
        List<ParticipationGroupCardResponse> responses = managementService.findParticipationGroupsByUser(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/participation-groups/summary")
    public ResponseEntity<List<ParticipationGroupSummaryResponse>> findParticipationGroupsSummary(
        @CurrentUser User user
    ) {
        return ResponseEntity.ok(managementService.findParticipationGroupsSummaryByUser(user));
    }

    @GetMapping("/my-groups/details")
    public ResponseEntity<List<MyGroupCardResponse>> findMyGroups(
        @CurrentUser User user
    ) {
        List<MyGroupCardResponse> responses = managementService.findMyGroupsByUser(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/my-groups/summary")
    public ResponseEntity<List<MyGroupSummaryResponse>> findMyGroupsSummary(
        @CurrentUser User user
    ) {
        List<MyGroupSummaryResponse> responses = managementService.findMyGroupsSummaryByUser(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/my-posts")
    public ResponseEntity<List<MyPostCardResponse>> findMyPosts(
        @CurrentUser User user,
        @RequestParam(required = false) Long lastPostId, @RequestParam int size
    ) {
        List<MyPostCardResponse> responses = managementService.findMyPostsByUser(user, lastPostId, size);
        return ResponseEntity.ok(responses);
    }
}
