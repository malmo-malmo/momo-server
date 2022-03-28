package com.momo.domain.user.application;

import com.momo.domain.aws.service.S3UploadService;
import com.momo.domain.aws.util.GenerateUploadPathUtil;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.application.dto.UserDtoMapper;
import com.momo.domain.user.application.dto.request.UserUpdateRequestDto;
import com.momo.domain.user.application.dto.response.UserUpdateResponseDto;
import com.momo.domain.user.domain.User;
import com.momo.domain.user.domain.UserRepository;
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

    @Transactional
    public UserUpdateResponseDto update(User loginUser, UserUpdateRequestDto dto) {
        User user = findByUser(loginUser);

        if (!user.isSameNickname(dto.getNickname())) {
            validateDuplicateNickname(dto.getNickname());
        }

        user.update(userDtoMapper.mapToUser(dto), userDtoMapper.mapToLocation(dto));

        return userDtoMapper.mapToUserUpdateResponseDto(user);
    }

    private User findByUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    @Transactional
    public void validateDuplicateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

    private String toImageUrl(MultipartFile multipartFile, Long userId) {
        return s3UploadService.upload(multipartFile, GenerateUploadPathUtil.getUserImage(userId));
    }
}
