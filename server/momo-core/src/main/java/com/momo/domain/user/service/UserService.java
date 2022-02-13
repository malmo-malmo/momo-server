package com.momo.domain.user.service;

import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.dto.UserUpdateResponse;
import com.momo.domain.user.entity.User;

public interface UserService {

    UserUpdateResponse update(User loginUser, UserUpdateRequest request);

    void validateDuplicateNickname(String nickname);
}
