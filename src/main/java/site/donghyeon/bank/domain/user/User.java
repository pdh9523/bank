package site.donghyeon.bank.domain.user;

import java.util.UUID;

public record User(UUID userId, String email) {
}
