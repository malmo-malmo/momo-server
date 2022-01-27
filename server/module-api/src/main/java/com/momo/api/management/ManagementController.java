package com.momo.api.management;

import com.momo.common.CurrentUser;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCountResponse;
import com.momo.domain.user.entity.User;
import com.momo.domain.management.service.ManagementService;
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

    @GetMapping("/group/participation/count")
    public ResponseEntity<ParticipatingGroupCountResponse> findParticipatingGroupCount(@CurrentUser User user) {
        ParticipatingGroupCountResponse response = managementService.findParticipatingGroupCountByUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/groups/participation")
    public ResponseEntity<List<ParticipatingGroupCardResponse>> findParticipatingGroups(@CurrentUser User user) {
        List<ParticipatingGroupCardResponse> responses = managementService.findParticipatingGroupsByUser(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<MyPostCardResponse>> findMyPosts(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<MyPostCardResponse> responses = managementService.findMyPostsByUser(user, page, size);
        return ResponseEntity.ok(responses);
    }
}
