package com.momo.user.application;

import com.momo.aws.service.S3UploadService;
import com.momo.aws.util.GenerateUploadPathUtil;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.user.application.dto.UserAssembler;
import com.momo.user.application.dto.request.UserUpdateRequest;
import com.momo.user.application.dto.response.UserImageUpdateResponse;
import com.momo.user.application.dto.response.UserResponse;
import com.momo.user.application.dto.response.UserUpdateResponse;
import com.momo.user.domain.User;
import com.momo.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;

    @Transactional(readOnly = true)
    public UserResponse findMyInformation(User loginUser) {
        User user = findByUser(loginUser);

        return UserAssembler.mapToUserResponse(user);
    }

    @Transactional
    public UserUpdateResponse updateMyInformation(User loginUser, UserUpdateRequest request) {
        User user = findByUser(loginUser);

        if (!user.isSameNickname(request.getNickname())) {
            validateDuplicateNickname(request.getNickname());
        }

        user.update(request.getNickname(), UserAssembler.mapToLocation(request));

        return UserAssembler.mapToUserUpdateResponse(user);
    }

    @Transactional
    public UserImageUpdateResponse updateImage(User loginUser, MultipartFile imageFile) {
        User user = findByUser(loginUser);
        String imageUrl = s3UploadService.upload(imageFile, GenerateUploadPathUtil.getUserImagePath(user.getId()));

        user.updateImageUrl(imageUrl);

        return new UserImageUpdateResponse(imageUrl);
    }

    private User findByUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    @Transactional(readOnly = true)
    public void validateDuplicateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }
}
