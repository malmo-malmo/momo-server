package com.momo.api.user;

import com.momo.api.auth.CurrentUser;
import com.momo.api.user.dto.UserMapper;
import com.momo.api.user.dto.request.UserUpdateRequest;
import com.momo.api.user.dto.response.UserImageUpdateResponse;
import com.momo.api.user.dto.response.UserResponse;
import com.momo.api.user.dto.response.UserUpdateResponse;
import com.momo.domain.user.application.UserService;
import com.momo.domain.user.application.dto.request.UserUpdateRequestDto;
import com.momo.domain.user.application.dto.response.UserImageUpdateResponseDto;
import com.momo.domain.user.application.dto.response.UserResponseDto;
import com.momo.domain.user.application.dto.response.UserUpdateResponseDto;
import com.momo.domain.user.domain.User;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserResponse> findMyInformation(
        @CurrentUser User user
    ) {
        UserResponseDto userResponseDto = userService.findMyInformation(user);

        return ResponseEntity.ok(userMapper.mapToUserResponse(userResponseDto));
    }

    @GetMapping("/duplicate-nickname")
    public ResponseEntity<Void> validateDuplicateNickname(
        @RequestParam String nickname
    ) {
        userService.validateDuplicateNickname(nickname);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserUpdateResponse> updateMyInformation(
        @CurrentUser User user,
        @Valid @ModelAttribute UserUpdateRequest request
    ) {
        UserUpdateRequestDto userUpdateRequestDto = userMapper.mapToUserUpdateRequestDto(request);
        UserUpdateResponseDto userUpdateResponseDto = userService.updateMyInformation(user, userUpdateRequestDto);

        return ResponseEntity.ok(userMapper.mapToUserUpdateResponse(userUpdateResponseDto));
    }

    @PutMapping("/update-image")
    public ResponseEntity<UserImageUpdateResponse> updateImage(
        @CurrentUser User user,
        @RequestPart(required = false) MultipartFile imageFile
    ) {
        UserImageUpdateResponseDto responseDto = userService.updateImage(user, imageFile);

        return ResponseEntity.ok(new UserImageUpdateResponse(responseDto.getImageUrl()));
    }
}
