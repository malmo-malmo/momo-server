package com.momo.user;

import com.momo.auth.CurrentUser;
import com.momo.user.dto.UserResponse;
import com.momo.user.dto.UserUpdateRequest;
import com.momo.user.dto.UserUpdateResponse;
import com.momo.user.entity.User;
import com.momo.user.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> findMyInformation(@CurrentUser User user) {
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @GetMapping("/duplicate-nickname")
    public ResponseEntity<Void> validateDuplicateNickname(@RequestParam String nickname) {
        userService.validateDuplicateNickname(nickname);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserUpdateResponse> updateMyInformation(@CurrentUser User user,
        @Valid @ModelAttribute UserUpdateRequest userUpdateRequest) {
        UserUpdateResponse response = userService.update(user, userUpdateRequest);
        return ResponseEntity.ok(response);
    }
}
