package spring_blogs.spring_blogs.dto.authDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_blogs.spring_blogs.Enum.AuthProviderTypeEnum;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String first_name;
    private String last_name;
    private String email;
    private AuthProviderTypeEnum authProviderType;
}
