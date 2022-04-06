package com.momo.user;

import com.momo.auth.CurrentUser;
import com.momo.common.dto.EnumResponse;
import com.momo.user.application.FavoriteCategoryService;
import com.momo.user.application.dto.request.FavoriteCategoriesUpdateRequest;
import com.momo.user.domain.User;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteCategoryController {

    private final FavoriteCategoryService favoriteCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<EnumResponse>> findFavoriteCategories(@CurrentUser User user) {
        List<EnumResponse> responses = favoriteCategoryService.findFavoriteCategoriesByUser(user);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/categories")
    public ResponseEntity<Void> updateFavoriteCategories(
        @CurrentUser User user,
        @Valid @RequestBody FavoriteCategoriesUpdateRequest request
    ) {
        favoriteCategoryService.updateFavoriteCategories(user, request);
        return ResponseEntity.ok().build();
    }
}
