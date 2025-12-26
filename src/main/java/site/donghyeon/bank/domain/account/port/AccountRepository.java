package site.donghyeon.bank.domain.account.port;

import site.donghyeon.bank.domain.account.Account;

import java.util.UUID;

public interface AccountRepository {
    Account findById(UUID accountId);
    Account save(Account account);
}
