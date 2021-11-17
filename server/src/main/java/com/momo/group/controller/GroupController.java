package com.momo.group.controller;

import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.GroupCreateRequest;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody GroupCreateRequest groupCreateRequest) throws URISyntaxException {
        Long groupId = groupService.create(user, groupCreateRequest);
        return ResponseEntity.created(new URI("/api/group/" + groupId)).build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<EnumResponse>> findCategories() {
        return ResponseEntity.ok(EnumResponse.listOfCategory());
    }
}
