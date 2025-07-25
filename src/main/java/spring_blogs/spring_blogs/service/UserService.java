package spring_blogs.spring_blogs.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_blogs.spring_blogs.Enum.AuthProviderTypeEnum;
import spring_blogs.spring_blogs.dto.authDto.AuthMessageResponseDto;
import spring_blogs.spring_blogs.dto.authDto.LoginUserDto;
import spring_blogs.spring_blogs.dto.authDto.RegisterUserDto;
import spring_blogs.spring_blogs.exception.exceptions.EmailAlreadyExistsException;
import spring_blogs.spring_blogs.exception.exceptions.InternalServerException;
import spring_blogs.spring_blogs.exception.exceptions.UnauthorizedExceptionMessage;
import spring_blogs.spring_blogs.model.Users;
import spring_blogs.spring_blogs.repository.UsersRepository;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /* helper function to check if the duplicate email's exists */
    private void checkIfEmailExists(RegisterUserDto userDto) {
        String email = userDto.getEmail().toLowerCase();
        if(this.usersRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email has been already taken, please enter another one!");
        }
    }

    /* register a new account for the user */
    @Transactional
    public AuthMessageResponseDto register(RegisterUserDto userDto) {
       /* validation for checking if duplicate email's already exists */
       this.checkIfEmailExists(userDto);

       /* hash password using argon-algorithm */
       String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        try {
            Users users = new Users();
            users.setFirstName(userDto.getFirst_name());
            users.setLastName(userDto.getLast_name());
            users.setEmail(userDto.getEmail().toLowerCase());
            users.setPassword(hashedPassword);
            users.setAuthProviderType(AuthProviderTypeEnum.EMAIL);
            this.usersRepository.save(users);

            return new AuthMessageResponseDto("User has been registered successfully!");
        } catch (Exception exception) {
            throw new InternalServerException("Some error occurred while registering the user, please try again!");
        }
    }

    /* login account */
    @Transactional
    public AuthMessageResponseDto login(LoginUserDto userDto) {
        Users user = this.usersRepository.findByEmail(userDto.getEmail()).
                orElseThrow(() -> new UnauthorizedExceptionMessage("Email is invalid or does not exists!"));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new UnauthorizedExceptionMessage("Incorrect password, please try again!");
        }

        return new AuthMessageResponseDto("Logged in successfully!");
    }
}
