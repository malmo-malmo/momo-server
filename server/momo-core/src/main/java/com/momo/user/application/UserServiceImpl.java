package com.momo.user.application;

import com.momo.aws.service.S3UploadService;
import com.momo.aws.util.GenerateUploadPathUtil;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.user.application.dto.UserDtoMapper;
import com.momo.user.application.dto.request.UserUpdateRequestDto;
import com.momo.user.application.dto.response.UserImageUpdateResponseDto;
import com.momo.user.application.dto.response.UserResponseDto;
import com.momo.user.application.dto.response.UserUpdateResponseDto;
import com.momo.user.domain.User;
import com.momo.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;
    private final UserDtoMapper userDtoMapper;

    @Transactional(readOnly = true)
    public UserResponseDto findMyInformation(User loginUser) {
        User user = findByUser(loginUser);

        return userDtoMapper.mapToUserResponseDto(user);
    }

    @Transactional
    public UserUpdateResponseDto updateMyInformation(User loginUser, UserUpdateRequestDto dto) {
        User user = findByUser(loginUser);

        if (!user.isSameNickname(dto.getNickname())) {
            validateDuplicateNickname(dto.getNickname());
        }

        user.update(dto.getNickname(), userDtoMapper.mapToLocation(dto));

        return userDtoMapper.mapToUserUpdateResponseDto(user);
    }

    @Transactional
    public UserImageUpdateResponseDto updateImage(User loginUser, MultipartFile imageFile) {
        User user = findByUser(loginUser);
        String imageUrl = s3UploadService.upload(imageFile, GenerateUploadPathUtil.getUserImage(user.getId()));

        user.updateImageUrl(imageUrl);

        return new UserImageUpdateResponseDto(imageUrl);
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
