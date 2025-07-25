package spring_blogs.spring_blogs.dto.authDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Invalid email!")
    private String email;

    @NotEmpty(message = "Password should not be empty!")
    private String password;
}
