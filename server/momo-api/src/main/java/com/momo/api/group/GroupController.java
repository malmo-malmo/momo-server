package com.momo.api.group;

import com.momo.api.auth.CurrentUser;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupCreateResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.service.GroupService;
import com.momo.domain.user.entity.User;
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

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> findGroup(
        @CurrentUser User user,
        @PathVariable Long id
    ) {
        GroupResponse groupResponse = groupService.findGroupById(user, id);
        return ResponseEntity.ok(groupResponse);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<EnumResponse>> findGroupCategories() {
        return ResponseEntity.ok(EnumResponse.listOfCategory());
    }

    @PatchMapping("/{id}/manager/{userId}")
    public ResponseEntity<Void> updateManagerByUserId(
        @CurrentUser User user,
        @PathVariable Long id, @PathVariable Long userId
    ) {
        groupService.updateManagerByUserId(user, id, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/end")
    public ResponseEntity<Void> endGroupById(
        @CurrentUser User user,
        @PathVariable Long id
    ) {
        groupService.endGroupById(user, id);
        return ResponseEntity.ok().build();
    }
}
