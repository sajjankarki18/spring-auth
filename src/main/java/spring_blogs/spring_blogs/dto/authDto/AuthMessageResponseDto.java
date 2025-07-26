package spring_blogs.spring_blogs.dto.authDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class AuthMessageResponseDto {
    private String message;
    private String accessToken;
    private UserResponseDto user;
}
