package com.momo.user.service;

import com.momo.common.dto.GroupCategoryRequest;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
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

  public void updateGroupCategories(User loginUser, GroupCategoryRequest request) {
    User user = findByUser(loginUser);
    user.updateGroupCategories(request.toGroupCategories());
  }

  public User findByUser(User user) {
    return userRepository.findById(user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USER_ID));
  }
}
