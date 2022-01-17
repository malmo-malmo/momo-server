package com.momo.api.post;

import com.momo.common.CurrentUser;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.PostCardsRequest;
import com.momo.domain.post.service.PostService;
import com.momo.domain.user.entity.User;
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
    public ResponseEntity<List<PostCardResponse>> findPageByCardsRequest(@CurrentUser User user,
        @ModelAttribute @Valid PostCardsRequest postCardsRequest) {
        List<PostCardResponse> postCardResponses = postService.findPageByGroupIdAndType(user, postCardsRequest);
        return ResponseEntity.ok(postCardResponses);
    }
}
