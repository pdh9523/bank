package site.donghyeon.bank.presentation.resolver;

import java.util.UUID;

public record CurrentUser(
        UUID userId,
        String email
) {
}
