package com.momo.post;

import com.momo.auth.CurrentUser;
import com.momo.post.dto.CommentsRequest;
import com.momo.post.dto.CommentsResponse;
import com.momo.post.service.CommentService;
import com.momo.user.entity.User;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @GetMapping("/paging")
    public ResponseEntity<CommentsResponse> findPageByPost(
        @CurrentUser User user,
        @Valid @ModelAttribute CommentsRequest commentsRequest
    ) {
        CommentsResponse response = commentService.findPageByPostId(user, commentsRequest);
        return ResponseEntity.ok(response);
    }
}
