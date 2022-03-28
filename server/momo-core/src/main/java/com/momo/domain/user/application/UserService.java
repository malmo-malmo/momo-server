package com.momo.domain.user.application;

import com.momo.domain.user.application.dto.request.UserUpdateRequestDto;
import com.momo.domain.user.application.dto.response.UserUpdateResponseDto;
import com.momo.domain.user.domain.User;

public interface UserService {

    UserUpdateResponseDto update(User loginUser, UserUpdateRequestDto dto);

    void validateDuplicateNickname(String nickname);
}
