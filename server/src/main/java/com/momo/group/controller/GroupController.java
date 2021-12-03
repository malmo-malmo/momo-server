package com.momo.group.controller;

import com.momo.auth.domain.CurrentUser;
import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.GroupCardResponse;
import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.controller.dto.GroupResponse;
import com.momo.group.controller.dto.GroupSearchConditionRequest;
import com.momo.group.service.GroupService;
import com.momo.user.domain.model.User;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @ApiOperation(value = "모임 생성")
    @PostMapping("/group")
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody GroupCreateRequest groupCreateRequest) throws URISyntaxException {
        Long groupId = groupService.create(user, groupCreateRequest);
        return ResponseEntity.created(new URI("/api/group/" + groupId)).build();
    }

    @ApiOperation(value = "모임 상세 페이지 조회")
    @GetMapping("/group/{id}")
    public ResponseEntity<GroupResponse> find(@CurrentUser User user, @PathVariable Long id) {
        GroupResponse groupResponse = groupService.findById(user, id);
        return ResponseEntity.ok(groupResponse);
    }

    @ApiOperation(value = "모임 목록 조회(검색)")
    @GetMapping("/groups/search/paging")
    public ResponseEntity<List<GroupCardResponse>> findPageBySearchCondition(
        @Valid @ModelAttribute GroupSearchConditionRequest request) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageBySearchCondition(request);
        return ResponseEntity.ok(groupCardResponses);
    }

    @ApiOperation(value = "모임 목록 조회(내 학교 더보기)")
    @GetMapping("/groups/user-university/paging")
    public ResponseEntity<List<GroupCardResponse>> findPageByUserUniversity(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserUniversity(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @ApiOperation(value = "모임 목록 조회(주변 더보기)")
    @GetMapping("/groups/user-district/paging")
    public ResponseEntity<List<GroupCardResponse>> findPageByUserLocation(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserDistrict(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @ApiOperation(value = "모임 목록 조회(추천 더보기)")
    @GetMapping("/groups/user-categories/paging")
    public ResponseEntity<List<GroupCardResponse>> findPageByUserCategories(@CurrentUser User user,
        @RequestParam int page, @RequestParam int size) {
        List<GroupCardResponse> groupCardResponses = groupService.findPageByUserCategories(user, page, size);
        return ResponseEntity.ok(groupCardResponses);
    }

    @ApiOperation(value = "모임 카테고리 목록 조회")
    @GetMapping("/group/categories")
    public ResponseEntity<List<EnumResponse>> findGroupCategories() {
        return ResponseEntity.ok(EnumResponse.listOfCategory());
    }
}
