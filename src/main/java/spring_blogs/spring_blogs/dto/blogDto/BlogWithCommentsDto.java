package spring_blogs.spring_blogs.dto.blogDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_blogs.spring_blogs.Enum.BlogStatusEnum;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogWithCommentsDto {
    private String title;
    private String description;
    private BlogStatusEnum status;
    private List<spring_blogs.spring_blogs.dto.blogDto.CommentResponseDto> comments = new ArrayList<>();
}
