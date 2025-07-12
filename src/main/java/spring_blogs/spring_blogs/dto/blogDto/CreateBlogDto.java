package spring_blogs.spring_blogs.dto.blogDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_blogs.spring_blogs.Enum.BlogStatusEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlogDto {
    @NotEmpty(message = "The title should not be empty!")
    private String title;

    @NotEmpty(message = "The description should not be empty!")
    private String description;

    @NotNull(message = "The status is required!")
    private BlogStatusEnum status;
}
