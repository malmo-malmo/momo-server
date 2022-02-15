package com.momo.api.post;

import com.momo.api.auth.CurrentUser;
import com.momo.domain.post.dto.CommentCreateRequest;
import com.momo.domain.post.dto.CommentResponse;
import com.momo.domain.post.service.CommentService;
import com.momo.domain.user.entity.User;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> create(@CurrentUser User user,
        @Valid @RequestBody CommentCreateRequest commentCreateRequest) throws URISyntaxException {
        CommentResponse commentResponse = commentService.create(user, commentCreateRequest);
        return ResponseEntity.created(new URI("/api/comment/" + commentResponse.getId())).body(commentResponse);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@CurrentUser User user, @PathVariable Long commentId) {
        commentService.deleteComment(commentId, user);
        return ResponseEntity.noContent().build();
    }
}
