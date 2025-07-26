package spring_blogs.spring_blogs.dto.authDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserMessageDto {
    private String message;
}
