package spring_blogs.spring_blogs.dto.blogDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_blogs.spring_blogs.Enum.BlogStatusEnum;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDto {
    private UUID id;
    private String title;
    private String description;
    private BlogStatusEnum status;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;
}
