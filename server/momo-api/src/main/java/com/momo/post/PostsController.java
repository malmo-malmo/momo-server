package com.momo.post;

import com.momo.auth.CurrentUser;
import com.momo.post.dto.PostCardResponse;
import com.momo.post.dto.PostCardsRequest;
import com.momo.post.service.PostService;
import com.momo.user.domain.model.User;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;

    @GetMapping("/paging")
    public ResponseEntity<List<PostCardResponse>> findPageByGroup(
        @CurrentUser User user,
        @Valid @ModelAttribute PostCardsRequest postCardsRequest
    ) {
        List<PostCardResponse> postCardResponses = postService.findPageByGroupId(user, postCardsRequest);
        return ResponseEntity.ok(postCardResponses);
    }
}
