package spring_blogs.spring_blogs.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_blogs.spring_blogs.dto.authDto.AuthMessageResponseDto;
import spring_blogs.spring_blogs.dto.authDto.LoginUserDto;
import spring_blogs.spring_blogs.dto.authDto.RegisterUserDto;
import spring_blogs.spring_blogs.service.UserService;

@RestController
@RequestMapping("/auth/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* register a new account */
    @PostMapping("/register")
    private ResponseEntity<AuthMessageResponseDto> register(@Valid @RequestBody RegisterUserDto userDto) {
        AuthMessageResponseDto user = this.userService.register(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /* login account */
    @PostMapping("/login")
    private ResponseEntity<AuthMessageResponseDto> login(@Valid @RequestBody LoginUserDto userDto) {
        AuthMessageResponseDto user = this.userService.login(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
