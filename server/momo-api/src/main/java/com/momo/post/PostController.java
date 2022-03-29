package com.momo.post;

import com.momo.auth.CurrentUser;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.post.dto.PostUpdateRequest;
import com.momo.domain.post.service.PostService;
import com.momo.domain.user.entity.User;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(
        @CurrentUser User user,
        @Valid @ModelAttribute PostCreateRequest postCreateRequest
    ) throws URISyntaxException, IOException {
        PostResponse response = postService.create(user, postCreateRequest);
        return ResponseEntity.created(new URI("/api/post/" + response.getId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> find(
        @CurrentUser User user,
        @PathVariable Long id
    ) {
        PostResponse postResponse = postService.findById(user, id);
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping
    public ResponseEntity<Void> update(
        @CurrentUser User user,
        @Valid @ModelAttribute PostUpdateRequest request
    ) {
        postService.updatePost(request, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(
        @CurrentUser User user,
        @PathVariable Long postId
    ) {
        postService.deletePost(postId, user);
        return ResponseEntity.noContent().build();
    }
}
