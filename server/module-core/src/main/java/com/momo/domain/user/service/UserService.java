package com.momo.domain.user.service;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.domain.model.Category;
import com.momo.domain.group.dto.CategoryRequest;
import com.momo.domain.user.domain.model.User;
import com.momo.domain.user.domain.repository.UserRepository;
import com.momo.domain.user.dto.UserUpdateRequest;
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
