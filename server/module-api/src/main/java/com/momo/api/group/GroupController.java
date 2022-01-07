package com.momo.api.group;

import com.momo.common.CurrentUser;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
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

    @GetMapping("/group/{id}")
    public ResponseEntity<GroupResponse> find(@CurrentUser User user, @PathVariable Long id) {
        GroupResponse groupResponse = groupService.findById(user, id);
        return ResponseEntity.ok(groupResponse);
    }

    @GetMapping("/groups/search/paging")
    public ResponseEntity<List<GroupCardResponse>> findPageBySearchCondition(@CurrentUser User user,
        @Valid @ModelAttribute GroupSearchConditionRequest request) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageBySearchCondition(user, request);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/groups/user-university/paging")
    public ResponseEntity<List<GroupCardResponse>> findPageByUserUniversity(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserUniversity(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/groups/user-district/paging")
    public ResponseEntity<List<GroupCardResponse>> findPageByUserLocation(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserDistrict(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/groups/user-categories/paging")
    public ResponseEntity<List<GroupCardResponse>> findPageByUserCategories(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserCategories(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/group/categories")
    public ResponseEntity<List<EnumResponse>> findGroupCategories() {
        return ResponseEntity.ok(EnumResponse.listOfCategory());
    }

    @PatchMapping("/group/{id}/manager/{userId}")
    public ResponseEntity<Void> updateManagerByUserId(@CurrentUser User user, @PathVariable Long id,
        @PathVariable Long userId) {
        groupService.updateManagerByUserId(user, id, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/group/{id}/end")
    public ResponseEntity<Void> endGroupById(@CurrentUser User user, @PathVariable Long id) {
        groupService.endGroupById(user, id);
        return ResponseEntity.ok().build();
    }
}
