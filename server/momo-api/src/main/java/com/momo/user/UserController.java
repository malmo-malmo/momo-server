package com.momo.user;

import com.momo.auth.CurrentUser;
import com.momo.user.application.UserService;
import com.momo.user.application.dto.request.UserUpdateRequest;
import com.momo.user.application.dto.response.UserImageUpdateResponse;
import com.momo.user.application.dto.response.UserResponse;
import com.momo.user.application.dto.response.UserUpdateResponse;
import com.momo.user.domain.model.User;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> findMyInformation(@CurrentUser User user) {
        UserResponse response = userService.findMyInformation(user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/duplicate-nickname")
    public ResponseEntity<Void> validateDuplicateNickname(@RequestParam String nickname) {
        userService.validateDuplicateNickname(nickname);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserUpdateResponse> updateMyInformation(
        @CurrentUser User user,
        @Valid @RequestBody UserUpdateRequest request
    ) {
        UserUpdateResponse response = userService.updateMyInformation(user, request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-image")
    public ResponseEntity<UserImageUpdateResponse> updateImage(
        @CurrentUser User user,
        @RequestParam(required = false) MultipartFile imageFile
    ) {
        UserImageUpdateResponse response = userService.updateImage(user, imageFile);

        return ResponseEntity.ok(response);
    }
}
