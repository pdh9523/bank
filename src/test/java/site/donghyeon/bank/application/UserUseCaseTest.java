package site.donghyeon.bank.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.donghyeon.bank.application.user.UserService;
import site.donghyeon.bank.application.user.command.GetUserInfoCommand;
import site.donghyeon.bank.application.user.command.RegisterCommand;
import site.donghyeon.bank.application.user.result.GetUserInfoResult;
import site.donghyeon.bank.application.user.result.RegisterResult;
import site.donghyeon.bank.domain.user.User;
import site.donghyeon.bank.application.user.exception.EmailAlreadyExistsException;
import site.donghyeon.bank.application.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    private static final UUID TEST_USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private static final String TEST_EMAIL = "user@example.com";
    private static final String TEST_PASSWORD = "Secret123!";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void 회원가입_테스트() {
        User savedUser = new User(TEST_USER_ID, TEST_EMAIL);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        RegisterCommand command = new RegisterCommand(TEST_EMAIL, TEST_PASSWORD);

        RegisterResult result = userService.register(command);

        assertThat(result.userId()).isEqualTo(savedUser.getUserId());
        assertThat(result.email()).isEqualTo(savedUser.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void 유저_조회_테스트() {
        User existing = new User(TEST_USER_ID, TEST_EMAIL);
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(existing));

        GetUserInfoCommand command = new GetUserInfoCommand(TEST_USER_ID);

        GetUserInfoResult result = userService.getUserInfo(command);

        assertThat(result).isNotNull();
        assertThat(result.userId()).isEqualTo(TEST_USER_ID);
        assertThat(result.email()).isEqualTo(existing.getEmail());

        verify(userRepository).findById(TEST_USER_ID);
    }

    @Test
    void 이메일_중복_시_에러_반환() {
        when(userRepository.existsByEmail(TEST_EMAIL)).thenReturn(true);

        RegisterCommand command = new RegisterCommand(TEST_EMAIL, TEST_PASSWORD);

        assertThatThrownBy(() -> userService.register(command))
                .isInstanceOf(EmailAlreadyExistsException.class);

        verify(userRepository).existsByEmail(TEST_EMAIL);
        verifyNoMoreInteractions(userRepository);
    }
}
