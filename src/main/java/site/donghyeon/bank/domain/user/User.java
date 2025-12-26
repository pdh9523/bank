package site.donghyeon.bank.domain.user;

import java.util.UUID;

public class User {
    private UUID userId;
    private final String email;

    public User(UUID userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public static User newUser(String email) {
        return new User(UUID.randomUUID(), email);
    }

    public UUID getUserId() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }
}
