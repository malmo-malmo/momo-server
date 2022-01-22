package com.momo.domain.user.service;

import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.entity.User;

public interface UserService {

    void update(User loginUser, UserUpdateRequest request);

    void validateDuplicateNickname(String nickname);
}
