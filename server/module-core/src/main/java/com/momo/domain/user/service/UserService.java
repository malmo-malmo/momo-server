package com.momo.domain.user.service;

import com.momo.domain.aws.service.S3UploadService;
import com.momo.domain.aws.util.GenerateUploadPathUtil;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.entity.FavoriteCategories;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;

    public void update(User loginUser, UserUpdateRequest request) {
        User user = findByUser(loginUser);
        if (!user.isSameNickname(request.getNickname())) {
            validateDuplicateNickname(request.getNickname());
        }
        user.update(request.toEntity(), convertToImageUrl(request.getImage(), user.getId()));
    }

    public void validateDuplicateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

    public String convertToImageUrl(MultipartFile multipartFile, Long userId) {
        return s3UploadService.upload(multipartFile, GenerateUploadPathUtil.getUserImage(userId));
    }

    @Transactional(readOnly = true)
    public List<EnumResponse> findFavoriteCategoriesByUser(User loginUser) {
        FavoriteCategories favoriteCategories = loginUser.getFavoriteCategories();
        return EnumResponse.listOfFavoriteCategories(favoriteCategories);
    }

    public void updateFavoriteCategories(User loginUser, FavoriteCategoriesUpdateRequest request) {
        User user = findByUser(loginUser);
        user.updateFavoriteCategories(request.getFavoriteCategories());
    }

    public User findByUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
