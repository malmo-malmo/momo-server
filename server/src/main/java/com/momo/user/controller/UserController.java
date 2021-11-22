package com.momo.user.controller;

import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.CategoryRequest;
import com.momo.auth.domain.CurrentUser;
import com.momo.user.controller.dto.UserUpdateRequest;
import com.momo.user.domain.model.User;
import com.momo.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "닉네임 중복 확인")
    @GetMapping("/validate/nickname/{nickname}")
    public ResponseEntity<Boolean> validateNickname(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.validateNickname(nickname));
    }

    @ApiOperation(value = "내 정보 수정")
    @PatchMapping
    public ResponseEntity<Void> update(@CurrentUser User user,
        @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(user, userUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "관심 카테고리 수정")
    @PatchMapping("/categories")
    public ResponseEntity<Void> updateCategories(@CurrentUser User user,
        @Valid @RequestBody CategoryRequest categoryRequest) {
        userService.updateCategories(user, categoryRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "검색 가능한 지역 목록 조회")
    @GetMapping("/locations")
    public ResponseEntity<List<EnumResponse>> findLocations() {
        return ResponseEntity.ok(EnumResponse.listOfLocation());
    }
}
