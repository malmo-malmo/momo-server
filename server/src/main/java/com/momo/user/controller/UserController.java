package com.momo.user.controller;

import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.CategoryRequest;
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

    @GetMapping("/validate/nickname")
    public ResponseEntity<Boolean> validateNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(userService.validateNickname(nickname));
    }

    @PatchMapping("/categories")
    public ResponseEntity<Void> updateCategories(@CurrentUser User user,
        @Valid @RequestBody CategoryRequest categoryRequest) {
        userService.updateCategories(user, categoryRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/locations")
    public ResponseEntity<List<EnumResponse>> findLocations() {
        return ResponseEntity.ok(EnumResponse.listOfLocation());
    }
}
