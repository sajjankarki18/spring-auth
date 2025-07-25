package spring_blogs.spring_blogs.dto.blogDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_blogs.spring_blogs.Enum.BlogStatusEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogWithCommentsDto {
    private UUID id;
    private String title;
    private String description;
    private BlogStatusEnum status;
    private List<spring_blogs.spring_blogs.dto.blogDto.CommentResponseDto> comments = new ArrayList<>();
}
