package spring_blogs.spring_blogs.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_blogs.spring_blogs.model.Comment;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {}
