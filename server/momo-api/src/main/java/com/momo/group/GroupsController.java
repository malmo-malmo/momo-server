package com.momo.group;

import com.momo.auth.CurrentUser;
import com.momo.group.application.GroupsService;
import com.momo.group.application.dto.request.GroupSearchConditionRequest;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.user.domain.User;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupsController {

    private final GroupsService groupsService;

    @GetMapping("/search-v1/paging")
    public ResponseEntity<List<GroupCardResponse>> findBySearchConditionV1(
        @CurrentUser User user,
        @Valid @ModelAttribute GroupSearchConditionRequest request
    ) {
        List<GroupCardResponse> groupCardResponses = groupsService.findPageBySearchConditionV1(user, request);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/search-v2/paging")
    public ResponseEntity<List<GroupCardResponse>> findBySearchConditionV2(
        @CurrentUser User user,
        @Valid @ModelAttribute GroupSearchConditionRequest request
    ) {
        List<GroupCardResponse> groupCardResponses = groupsService.findPageBySearchConditionV2(user, request);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/user-university/paging")
    public ResponseEntity<List<GroupCardResponse>> findByUserUniversity(
        @CurrentUser User user,
        @RequestParam(required = false) Long lastGroupId, @RequestParam int size
    ) {
        List<GroupCardResponse> groupCardResponses = groupsService.findPageByUserUniversity(user, lastGroupId, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/user-district/paging")
    public ResponseEntity<List<GroupCardResponse>> findByUserLocation(
        @CurrentUser User user,
        @RequestParam(required = false) Long lastGroupId, @RequestParam int size
    ) {
        List<GroupCardResponse> groupCardResponses = groupsService.findPageByUserDistrict(user, lastGroupId, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/user-categories/paging")
    public ResponseEntity<List<GroupCardResponse>> findByUserCategories(
        @CurrentUser User user,
        @RequestParam(required = false) Long lastGroupId, @RequestParam int size
    ) {
        List<GroupCardResponse> groupCardResponses = groupsService.findPageByUserCategories(user, lastGroupId, size);
        return ResponseEntity.ok(groupCardResponses);
    }
}
