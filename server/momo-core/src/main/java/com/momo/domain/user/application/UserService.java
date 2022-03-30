package com.momo.domain.user.application;

import com.momo.domain.user.application.dto.request.UserUpdateRequestDto;
import com.momo.domain.user.application.dto.response.UserImageUpdateResponseDto;
import com.momo.domain.user.application.dto.response.UserResponseDto;
import com.momo.domain.user.application.dto.response.UserUpdateResponseDto;
import com.momo.domain.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserResponseDto findMyInformation(User loginUser);

    UserUpdateResponseDto updateMyInformation(User loginUser, UserUpdateRequestDto dto);

    UserImageUpdateResponseDto updateImage(User loginUser, MultipartFile imageFile);

    void validateDuplicateNickname(String nickname);
}
