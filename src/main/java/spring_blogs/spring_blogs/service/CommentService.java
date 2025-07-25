package spring_blogs.spring_blogs.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_blogs.spring_blogs.dto.blogDto.CommentResponseDto;
import spring_blogs.spring_blogs.dto.commentDto.AddCommentDto;
import spring_blogs.spring_blogs.dto.commentDto.UpdateCommentDto;
import spring_blogs.spring_blogs.exception.exceptions.BlogNotFoundException;
import spring_blogs.spring_blogs.exception.exceptions.CommentNotFoundException;
import spring_blogs.spring_blogs.exception.exceptions.InternalServerException;
import spring_blogs.spring_blogs.model.Blog;
import spring_blogs.spring_blogs.model.Comment;
import spring_blogs.spring_blogs.repository.BlogRepository;
import spring_blogs.spring_blogs.repository.CommentRepository;
import java.util.*;

@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    @Transactional
    public CommentResponseDto addComment(UUID blogId, AddCommentDto commentDto) {
      try {
          Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new BlogNotFoundException("Blog with " + blogId + " has not found!"));

          Comment comment = new Comment();
          comment.setComment(commentDto.getComment());
          comment.setBlog(blog);
          Comment savedComment = commentRepository.save(comment);

          log.info("Comment has been added!");
          return new CommentResponseDto(savedComment.getId(), savedComment.getComment(), savedComment.getBlog().getId(), savedComment.getCreated_at(), savedComment.getUpdated_at(), savedComment.getDeleted_at());
      } catch (Exception exception) {
          log.error("Error occurred while adding a new comment!");
          throw new RuntimeException("Some error occurred while adding a comment", exception);
      }
    }

    @Transactional
    public CommentResponseDto getCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with " + commentId + " not found"));
        return new CommentResponseDto(comment.getId(), comment.getComment(), comment.getBlog().getId(), comment.getCreated_at(), comment.getUpdated_at(), comment.getDeleted_at());
    }

    @Transactional
    public List<CommentResponseDto> getAllComments() {
        try {
            List<Comment> comments = commentRepository.findAll();
            log.info("Comment has been fetched!");
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
            log.error("Error occurred while fetching comments!");
            throw new InternalServerException("Some error occurred while fetching comment lists!");
        }
    }

    @Transactional
    public CommentResponseDto updateComment(UpdateCommentDto commentDto, UUID id) {
        Comment comment = commentRepository.findById(id).
                orElseThrow(() -> new CommentNotFoundException("Comment with " + id + " not found!"));
        comment.setComment(comment.getComment());
        comment.setUpdated_at(new Date());

        Comment savedComment = commentRepository.save(comment);
        log.info("Comment has been updated");
        return new CommentResponseDto(savedComment.getId(), comment.getComment(), comment.getBlog().getId(), comment.getCreated_at(), comment.getUpdated_at(), comment.getDeleted_at());
    }

    @Transactional
    public Map<String, String> deleteComment(UUID id) {
        Comment comment = commentRepository.findById(id).
                orElseThrow(() -> new CommentNotFoundException("Comment with " + id + " not found!"));
        commentRepository.deleteById(comment.getId());

        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("message", "Comment has been deleted");

        log.info("Comment has been deleted!");
        return map;
    }

}
