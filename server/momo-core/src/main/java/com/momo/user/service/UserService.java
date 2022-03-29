package com.momo.user.service;

import com.momo.user.dto.UserUpdateRequest;
import com.momo.user.dto.UserUpdateResponse;
import com.momo.user.entity.User;

public interface UserService {

    UserUpdateResponse update(User loginUser, UserUpdateRequest request);

    void validateDuplicateNickname(String nickname);
}
