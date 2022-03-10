package com.momo.api.group;

import com.momo.api.auth.CurrentUser;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.service.GroupService;
import com.momo.domain.user.entity.User;
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

    private final GroupService groupService;

    @GetMapping("/search-v1/paging")
    public ResponseEntity<List<GroupCardResponse>> findBySearchConditionV1(@CurrentUser User user,
        @Valid @ModelAttribute GroupSearchConditionRequest request) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageBySearchConditionV1(user, request);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/search-v2/paging")
    public ResponseEntity<List<GroupCardResponse>> findBySearchConditionV2(@CurrentUser User user,
        @Valid @ModelAttribute GroupSearchConditionRequest request) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageBySearchConditionV2(user, request);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/user-university/paging")
    public ResponseEntity<List<GroupCardResponse>> findByUserUniversity(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserUniversity(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/user-district/paging")
    public ResponseEntity<List<GroupCardResponse>> findByUserLocation(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserDistrict(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @GetMapping("/user-categories/paging")
    public ResponseEntity<List<GroupCardResponse>> findByUserCategories(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserCategories(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }
}
