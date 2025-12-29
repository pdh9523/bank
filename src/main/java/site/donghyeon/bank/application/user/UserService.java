package site.donghyeon.bank.application.user;

import org.springframework.stereotype.Service;
import site.donghyeon.bank.application.user.command.GetUserInfoCommand;
import site.donghyeon.bank.application.user.result.GetUserInfoResult;
import site.donghyeon.bank.domain.user.User;
import site.donghyeon.bank.application.user.repository.UserRepository;

@Service
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserInfoResult getUserInfo(GetUserInfoCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseGet(() -> createUser(command));
        return GetUserInfoResult.from(user);
    }

    private User createUser(GetUserInfoCommand command) {
        return userRepository.save(
                new User(
                        command.userId(),
                        command.email()
                )
        );
    }
}
