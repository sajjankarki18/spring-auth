package spring_blogs.spring_blogs.dto.commentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateCommentDto extends AddCommentDto {
    private UUID id;
}
