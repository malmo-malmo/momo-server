package com.momo.group;

import com.momo.auth.CurrentUser;
import com.momo.common.dto.EnumResponse;
import com.momo.group.application.GroupService;
import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.response.GroupCreateResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.user.domain.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupCreateResponse> createGroup(
        @CurrentUser User user,
        @Valid @ModelAttribute GroupCreateRequest groupCreateRequest
    ) throws URISyntaxException {
        GroupCreateResponse response = groupService.createGroup(user, groupCreateRequest);
        return ResponseEntity.created(new URI("/api/group/" + response.getId())).body(response);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponse> findGroup(
        @CurrentUser User user,
        @PathVariable Long groupId
    ) {
        GroupResponse groupResponse = groupService.findGroup(user, groupId);
        return ResponseEntity.ok(groupResponse);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<EnumResponse>> findGroupCategories() {
        List<EnumResponse> responses = EnumResponse.listOfCategory();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{groupId}/update-manger")
    public ResponseEntity<Void> updateManager(
        @CurrentUser User user,
        @PathVariable Long groupId,
        @RequestParam Long userId
    ) {
        groupService.updateManager(user, groupId, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{groupId}/end")
    public ResponseEntity<Void> endGroup(
        @CurrentUser User user,
        @PathVariable Long groupId
    ) {
        groupService.endGroup(user, groupId);
        return ResponseEntity.ok().build();
    }
}
