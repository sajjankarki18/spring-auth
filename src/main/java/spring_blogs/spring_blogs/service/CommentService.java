package spring_blogs.spring_blogs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_blogs.spring_blogs.dto.blogDto.CommentResponseDto;
import spring_blogs.spring_blogs.dto.commentDto.AddCommentDto;
import spring_blogs.spring_blogs.exception.exceptions.BlogNotFoundException;
import spring_blogs.spring_blogs.exception.exceptions.CommentNotFoundException;
import spring_blogs.spring_blogs.exception.exceptions.InternalServerException;
import spring_blogs.spring_blogs.model.Blog;
import spring_blogs.spring_blogs.model.Comment;
import spring_blogs.spring_blogs.repository.BlogRepository;
import spring_blogs.spring_blogs.repository.CommentRepository;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public CommentResponseDto addComment(UUID blogId, AddCommentDto commentDto) {
      try {
          Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new BlogNotFoundException("Blog with " + blogId + " has not found!"));

          Comment comment = new Comment();
          comment.setComment(commentDto.getComment());
          comment.setBlog(blog);
          Comment savedComment = commentRepository.save(comment);

          return new CommentResponseDto(savedComment.getId(), savedComment.getComment(), savedComment.getBlog().getId(), savedComment.getCreated_at(), savedComment.getUpdated_at(), savedComment.getDeleted_at());
      } catch (Exception exception) {
          throw new RuntimeException("Some error occurred while adding a comment", exception);
      }
    }

    public CommentResponseDto getCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with " + commentId + " not found"));
        return new CommentResponseDto(comment.getId(), comment.getComment(), comment.getBlog().getId(), comment.getCreated_at(), comment.getUpdated_at(), comment.getDeleted_at());
    }

    public List<CommentResponseDto> getAllComments() {
        try {
            List<Comment> comments = commentRepository.findAll();
            return comments.stream().map(comment -> {
                return new CommentResponseDto(
                        comment.getId(),
                        comment.getComment(),
                        comment.getBlog().getId(),
                        comment.getCreated_at(),
                        comment.getUpdated_at(),
                        comment.getDeleted_at()
                );
            }).toList();
        } catch (Exception exception) {
            throw new InternalServerException("Some error occurred while fetching comment lists!");
        }
    }

}
