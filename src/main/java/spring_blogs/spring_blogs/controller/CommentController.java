package spring_blogs.spring_blogs.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_blogs.spring_blogs.dto.ApiResponse;
import spring_blogs.spring_blogs.dto.blogDto.CommentResponseDto;
import spring_blogs.spring_blogs.dto.commentDto.AddCommentDto;
import spring_blogs.spring_blogs.service.CommentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{blogId}")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable UUID blogId, @Valid @RequestBody AddCommentDto commentDto) {
        CommentResponseDto comment = this.commentService.addComment(blogId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable UUID commentId) {
        CommentResponseDto comment = this.commentService.getCommentById(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/all-comments")
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getAllComments() {
        List<CommentResponseDto> comments = this.commentService.getAllComments();
        ApiResponse<List<CommentResponseDto>> response = new ApiResponse<>(comments);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
