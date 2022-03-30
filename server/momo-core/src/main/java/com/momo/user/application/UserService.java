package com.momo.user.application;

import com.momo.user.application.dto.request.UserUpdateRequestDto;
import com.momo.user.application.dto.response.UserImageUpdateResponseDto;
import com.momo.user.application.dto.response.UserResponseDto;
import com.momo.user.application.dto.response.UserUpdateResponseDto;
import com.momo.user.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserResponseDto findMyInformation(User loginUser);

    UserUpdateResponseDto updateMyInformation(User loginUser, UserUpdateRequestDto dto);

    UserImageUpdateResponseDto updateImage(User loginUser, MultipartFile imageFile);

    void validateDuplicateNickname(String nickname);
}
