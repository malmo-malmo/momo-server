package com.momo.post.controller;

import com.momo.post.controller.dto.PostCreateRequest;
import com.momo.post.controller.dto.PostResponse;
import com.momo.post.service.PostService;
import com.momo.security.CurrentUser;
import com.momo.user.domain.model.User;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody PostCreateRequest postCreateRequest) throws URISyntaxException {
        Long postId = postService.create(user, postCreateRequest);
        return ResponseEntity.created(new URI("/api/post/" + postId)).build();
    }

    @GetMapping
    public ResponseEntity<PostResponse> find(@CurrentUser User user, @RequestParam Long postId) {
        PostResponse postResponse = postService.find(user, postId);
        return ResponseEntity.ok(postResponse);
    }
}
