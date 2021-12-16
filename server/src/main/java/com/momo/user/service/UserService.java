package com.momo.user.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.controller.dto.CategoryRequest;
import com.momo.group.domain.model.Category;
import com.momo.user.controller.dto.UserUpdateRequest;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void update(User loginUser, UserUpdateRequest userUpdateRequest) {
        User user = findByUser(loginUser);
        if (user.isSameNickname(userUpdateRequest.getNickname())) {
            return;
        }
        validateDuplicateNickname(userUpdateRequest.getNickname());
        user.update(userUpdateRequest.toEntity());
    }

    public void validateDuplicateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

    public void updateCategories(User loginUser, CategoryRequest request) {
        User user = findByUser(loginUser);
        user.updateFavoriteCategories(Category.listOf(request.getCategories()));
    }

    public User findByUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
