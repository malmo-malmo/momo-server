package com.momo.group;

import com.momo.auth.CurrentUser;
import com.momo.group.application.FavoriteGroupService;
import com.momo.group.application.dto.request.FavoriteGroupCreateRequest;
import com.momo.group.application.dto.response.FavoriteGroupCardResponse;
import com.momo.group.application.dto.response.FavoriteGroupCountResponse;
import com.momo.user.domain.User;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteGroupController {

    private final FavoriteGroupService favoriteGroupService;

    @PostMapping("/group")
    public ResponseEntity<Void> createFavoriteGroup(
        @CurrentUser User user,
        @Valid @RequestBody FavoriteGroupCreateRequest request
    ) {
        favoriteGroupService.createFavoriteGroup(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/group/count")
    public ResponseEntity<FavoriteGroupCountResponse> findFavoriteGroupCount(
        @CurrentUser User user
    ) {
        return ResponseEntity.ok(favoriteGroupService.countFavoriteGroupsByUser(user));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<FavoriteGroupCardResponse>> findFavoriteGroups(
        @CurrentUser User user
    ) {
        List<FavoriteGroupCardResponse> responses = favoriteGroupService.findFavoriteGroupsByUser(user);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/group/{groupId}")
    public ResponseEntity<Void> deleteFavoriteGroup(
        @CurrentUser User user,
        @PathVariable Long groupId
    ) {
        favoriteGroupService.deleteFavoriteGroupByUserAndGroupId(user, groupId);
        return ResponseEntity.noContent().build();
    }
}
