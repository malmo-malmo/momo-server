package com.momo.api.user;

import com.momo.common.CurrentUser;
import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.dto.UserUpdateResponse;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/update")
    public ResponseEntity<UserUpdateResponse> updateMyInformation(@CurrentUser User user,
        @Valid @ModelAttribute UserUpdateRequest userUpdateRequest) {
        UserUpdateResponse response = userService.update(user, userUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<UserResponse> findMyInformation(@CurrentUser User user) {
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @GetMapping("/duplicate-nickname")
    public ResponseEntity<Void> validateDuplicateNickname(@RequestParam String nickname) {
        userService.validateDuplicateNickname(nickname);
        return ResponseEntity.ok().build();
    }
}
