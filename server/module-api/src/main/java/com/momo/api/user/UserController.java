package com.momo.api.user;

import com.momo.common.CurrentUser;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.user.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.user.dto.FavoriteGroupCardResponse;
import com.momo.domain.user.dto.FavoriteGroupCountResponse;
import com.momo.domain.user.dto.FavoriteGroupCreateRequest;
import com.momo.domain.user.dto.ParticipatingGroupCardResponse;
import com.momo.domain.user.dto.ParticipatingGroupCountResponse;
import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.service.FavoriteGroupService;
import com.momo.domain.user.service.GroupManagementService;
import com.momo.domain.user.service.UserService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FavoriteGroupService favoriteGroupService;
    private final GroupManagementService groupManagementService;

    @PostMapping("/update")
    public ResponseEntity<Void> updateMyInformation(@CurrentUser User user,
        @Valid @ModelAttribute UserUpdateRequest userUpdateRequest) {
        userService.update(user, userUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/favorite-group")
    public ResponseEntity<Void> createFavoriteGroup(@CurrentUser User user,
        @Valid @RequestBody FavoriteGroupCreateRequest request) throws URISyntaxException {
        Long id = favoriteGroupService.create(user, request);
        return ResponseEntity.created(new URI("/api/user/favorite-group/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<UserResponse> findMyInformation(@CurrentUser User user) {
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @GetMapping("/duplicate-nickname")
    public ResponseEntity<Void> validateDuplicateNickname(@RequestParam String nickname) {
        userService.validateDuplicateNickname(nickname);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favorite-group-count")
    public ResponseEntity<FavoriteGroupCountResponse> findFavoriteGroupCount(@CurrentUser User user) {
        return ResponseEntity.ok(favoriteGroupService.count(user));
    }

    @GetMapping("/favorite-groups")
    public ResponseEntity<List<FavoriteGroupCardResponse>> findFavoriteGroups(@CurrentUser User user) {
        List<FavoriteGroupCardResponse> responses = favoriteGroupService.findAll(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/favorite-categories")
    public ResponseEntity<List<EnumResponse>> findFavoriteCategories(@CurrentUser User user) {
        List<EnumResponse> responses = userService.findFavoriteCategoriesByUser(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/participating-group-count")
    public ResponseEntity<ParticipatingGroupCountResponse> findParticipatingGroupCount(@CurrentUser User user) {
        ParticipatingGroupCountResponse response = groupManagementService.findParticipatingGroupCountByUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/participating-groups")
    public ResponseEntity<List<ParticipatingGroupCardResponse>> findParticipatingGroups(@CurrentUser User user) {
        List<ParticipatingGroupCardResponse> responses = groupManagementService.findParticipatingGroupsByUser(user);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/favorite-categories")
    public ResponseEntity<Void> updateFavoriteCategories(@CurrentUser User user,
        @Valid @RequestBody FavoriteCategoriesUpdateRequest request) {
        userService.updateFavoriteCategories(user, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favorite-group/{id}")
    public ResponseEntity<Void> deleteFavoriteGroup(@CurrentUser User user, @PathVariable Long id) {
        favoriteGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
