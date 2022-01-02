package com.momo.api.group;

import com.momo.common.CurrentUser;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.service.GroupService;
import com.momo.domain.user.entity.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

    @ApiOperation(value = "모임장 권한 양도")
    @PatchMapping("/group/{id}/manager/{userId}")
    public ResponseEntity<Void> updateManagerByUserId(@CurrentUser User user, @PathVariable Long id,
        @PathVariable Long userId) {
        groupService.updateManagerByUserId(user, id, userId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "모임 종료")
    @PatchMapping("/group/{id}/end")
    public ResponseEntity<Void> endGroupById(@CurrentUser User user, @PathVariable Long id) {
        groupService.endGroupById(user, id);
        return ResponseEntity.ok().build();
    }
}
