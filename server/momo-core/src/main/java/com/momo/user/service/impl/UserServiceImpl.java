package com.momo.user.service.impl;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.aws.service.S3UploadService;
import com.momo.aws.util.GenerateUploadPathUtil;
import com.momo.user.dto.UserUpdateRequest;
import com.momo.user.dto.UserUpdateResponse;
import com.momo.user.entity.User;
import com.momo.user.repository.UserRepository;
import com.momo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;

    public UserUpdateResponse update(User loginUser, UserUpdateRequest request) {
        User user = findByUser(loginUser);
        if (!user.isSameNickname(request.getNickname())) {
            validateDuplicateNickname(request.getNickname());
        }
        user.update(request.toUser(), request.toLocation(), toImageUrl(request.getImage(), user.getId()));
        return UserUpdateResponse.of(user);
    }

    private User findByUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateDuplicateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

    private String toImageUrl(MultipartFile multipartFile, Long userId) {
        return s3UploadService.upload(multipartFile, GenerateUploadPathUtil.getUserImage(userId));
    }
}
