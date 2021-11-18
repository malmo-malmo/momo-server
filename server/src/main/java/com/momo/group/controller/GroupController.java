package com.momo.group.controller;

import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.controller.dto.GroupRequest;
import com.momo.group.controller.dto.GroupResponse;
import com.momo.group.service.GroupService;
import com.momo.security.CurrentUser;
import com.momo.user.domain.model.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
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
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/group")
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody GroupCreateRequest groupCreateRequest) throws URISyntaxException {
        Long groupId = groupService.create(user, groupCreateRequest);
        return ResponseEntity.created(new URI("/api/group/" + groupId)).build();
    }

    @GetMapping("/group")
    public ResponseEntity<GroupResponse> find(@CurrentUser User user, @RequestParam Long groupId) {
        GroupResponse groupResponse = groupService.find(user, groupId);
        return ResponseEntity.ok(groupResponse);
    }

    @GetMapping("/group/categories")
    public ResponseEntity<List<EnumResponse>> findCategories() {
        return ResponseEntity.ok(EnumResponse.listOfCategory());
    }
}
