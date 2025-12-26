package site.donghyeon.bank.domain;

import org.junit.jupiter.api.Test;
import site.donghyeon.bank.domain.user.User;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDomainTest {

    private static final UUID TEST_USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private static final String TEST_EMAIL = "user@example.com";
    private static final String OTHER_TEST_EMAIL = "other@example.com";

    @Test
    public void 유저_생성_테스트() {
        User user = new User(TEST_USER_ID, TEST_EMAIL);

        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isEqualTo(TEST_USER_ID);
        assertThat(user.getEmail()).isEqualTo(TEST_EMAIL);
    }

    @Test
    public void ID_생성_테스트() {
        User user1 = User.newUser(TEST_EMAIL);
        User user2 = User.newUser(OTHER_TEST_EMAIL);

        assertThat(user1).isNotNull();
        assertThat(user2).isNotNull();

        assertThat(user1.getUserId()).isNotEqualByComparingTo(user2.getUserId());
    }
}
