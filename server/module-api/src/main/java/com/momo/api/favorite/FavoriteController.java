package com.momo.api.favorite;

import com.momo.common.CurrentUser;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCountResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.domain.favorite.service.FavoriteService;
import com.momo.domain.user.entity.User;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/group")
    public ResponseEntity<Void> createFavoriteGroup(@CurrentUser User user,
        @Valid @RequestBody FavoriteGroupCreateRequest request) {
        favoriteService.createFavoriteGroup(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/group/count")
    public ResponseEntity<FavoriteGroupCountResponse> findFavoriteGroupCount(@CurrentUser User user) {
        return ResponseEntity.ok(favoriteService.countFavoriteGroupsByUser(user));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<FavoriteGroupCardResponse>> findFavoriteGroups(@CurrentUser User user) {
        List<FavoriteGroupCardResponse> responses = favoriteService.findFavoriteGroupsByUser(user);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<EnumResponse>> findFavoriteCategories(@CurrentUser User user) {
        List<EnumResponse> responses = favoriteService.findFavoriteCategoriesByUser(user);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/categories")
    public ResponseEntity<Void> updateFavoriteCategories(@CurrentUser User user,
        @Valid @RequestBody FavoriteCategoriesUpdateRequest request) {
        favoriteService.updateFavoriteCategories(user, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/group/{groupId}")
    public ResponseEntity<Void> deleteFavoriteGroup(@CurrentUser User user, @PathVariable Long groupId) {
        favoriteService.deleteFavoriteGroupByUserAndGroupId(user, groupId);
        return ResponseEntity.noContent().build();
    }
}
