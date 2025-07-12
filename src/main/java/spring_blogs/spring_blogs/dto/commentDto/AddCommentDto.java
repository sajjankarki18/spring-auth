package spring_blogs.spring_blogs.dto.commentDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentDto {
    @NotEmpty(message = "Comment field is empty!")
    private String comment;
}
