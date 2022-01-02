package com.momo.api.post;

import com.momo.common.CurrentUser;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.PostCardsRequest;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.post.service.PostService;
import com.momo.domain.user.entity.User;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ApiOperation(value = "게시물 생성", notes = "게시물 타입 = [NORMAL, NOTICE]")
    @PostMapping("/post")
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody PostCreateRequest postCreateRequest) throws URISyntaxException {
        Long postId = postService.create(user, postCreateRequest);
        return ResponseEntity.created(new URI("/api/post/" + postId)).build();
    }

    @ApiOperation(value = "게시물 상세 페이지 조회")
    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponse> find(@CurrentUser User user, @PathVariable Long id) {
        PostResponse postResponse = postService.findById(user, id);
        return ResponseEntity.ok(postResponse);
    }

    @ApiOperation(value = "게시물 목록 조회")
    @GetMapping("/posts/paging")
    public ResponseEntity<List<PostCardResponse>> findPageByCardsRequest(@CurrentUser User user,
                                                                         @ModelAttribute @Valid PostCardsRequest postCardsRequest) {
        List<PostCardResponse> postCardResponses = postService.findPageByGroupIdAndType(user, postCardsRequest);
        return ResponseEntity.ok(postCardResponses);
    }
}
