package com.momo.api.user;

import com.momo.common.CurrentUser;
import com.momo.domain.group.dto.CategoryRequest;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "내 정보 조회")
    @GetMapping
    public ResponseEntity<UserResponse> findMyInformation(@CurrentUser User user) {
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @ApiOperation(value = "닉네임 중복 확인")
    @GetMapping("/duplicate-nickname")
    public ResponseEntity<Void> validateDuplicateNickname(@RequestParam String nickname) {
        userService.validateDuplicateNickname(nickname);
        return ResponseEntity.ok().build();
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
}
