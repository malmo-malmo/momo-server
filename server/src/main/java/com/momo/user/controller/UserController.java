package com.momo.user.controller;

import com.momo.common.dto.EnumResponse;
import com.momo.common.dto.GroupCategoryRequest;
import com.momo.security.CurrentUser;
import com.momo.user.controller.dto.UserUpdateRequest;
import com.momo.user.domain.model.User;
import com.momo.user.service.UserService;
import java.util.List;
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

    @PatchMapping
    public ResponseEntity<Void> update(@CurrentUser User user,
        @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(user, userUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/duplicate-nickname")
    public ResponseEntity<Void> update(@RequestParam String nickname) {
        userService.findDuplicateNickname(nickname);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/categories")
    public ResponseEntity<Void> updateGroupCategories(@CurrentUser User user,
        @RequestBody GroupCategoryRequest groupCategoryRequest) {
        userService.updateGroupCategories(user, groupCategoryRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/locations")
    public ResponseEntity<List<EnumResponse>> findLocations() {
        return ResponseEntity.ok(EnumResponse.listOfLocation());
    }
}
