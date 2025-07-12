package spring_blogs.spring_blogs.dto.blogDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private UUID id;
    private String comment;
    private UUID blog_id;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;
}
