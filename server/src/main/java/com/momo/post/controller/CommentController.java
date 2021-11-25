package com.momo.post.controller;

import com.momo.auth.domain.CurrentUser;
import com.momo.post.controller.dto.CommentCreateRequest;
import com.momo.post.service.CommentService;
import com.momo.user.domain.model.User;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "게시물 댓글 등록")
    @PostMapping("/comment")
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        commentService.create(user, commentCreateRequest);
        return ResponseEntity.ok().build();
    }
}
