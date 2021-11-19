package com.momo.post.controller;

import com.momo.post.controller.dto.PostCardRequest;
import com.momo.post.controller.dto.PostCardResponse;
import com.momo.post.controller.dto.PostCreateRequest;
import com.momo.post.controller.dto.PostResponse;
import com.momo.post.service.PostService;
import com.momo.security.CurrentUser;
import com.momo.user.domain.model.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<Void> createPost(@CurrentUser User user,
        @Valid @RequestBody PostCreateRequest postCreateRequest) throws URISyntaxException {
        Long postId = postService.createPost(user, postCreateRequest);
        return ResponseEntity.created(new URI("/api/post/" + postId)).build();
    }

    @GetMapping("/post")
    public ResponseEntity<PostResponse> findPost(@CurrentUser User user, @RequestParam Long postId) {
        PostResponse postResponse = postService.findPost(user, postId);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/posts/paging")
    public ResponseEntity<List<PostCardResponse>> findPosts(@CurrentUser User user,
        @ModelAttribute @Valid PostCardRequest postCardRequest) {
        List<PostCardResponse> postCardResponses = postService.findPosts(user, postCardRequest);
        return ResponseEntity.ok(postCardResponses);
    }
}
