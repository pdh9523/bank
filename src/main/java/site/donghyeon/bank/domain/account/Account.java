package site.donghyeon.bank.domain.account;

import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.domain.account.enums.AccountStatus;
import site.donghyeon.bank.domain.account.exception.AccountAccessDeniedException;
import site.donghyeon.bank.domain.account.exception.AccountAlreadyClosedException;
import site.donghyeon.bank.domain.account.exception.InsufficientBalanceException;

import java.util.UUID;

public class Account {
    private final UUID accountId;
    private final UUID userId;
    private Money balance;
    private AccountStatus status;

    public Account(UUID accountId, UUID userId, Money balance, AccountStatus status) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.status = status;
    }

    public static Account open(UUID accountId, UUID userId) {
        return new Account(accountId, userId, Money.zero(), AccountStatus.OPEN);
    }

    public void close(UUID userId) {
        if (this.status == AccountStatus.CLOSED) {
            throw new AccountAlreadyClosedException(this.accountId);
        }
        verifyOwner(userId);
        this.status = AccountStatus.CLOSED;
    }

    public void deposit(Money amount) {
        this.balance = balance.add(amount);
    }

    public void withdraw(Money money) {
        if (this.balance.amount() < money.amount()) {
            throw new InsufficientBalanceException(this.balance, money);
        }
        this.balance = balance.subtract(money);
    }

    public UUID getAccountId() {
        return this.accountId;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public Money getBalance() {
        return this.balance;
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public void verifyOwner(UUID userId) {
        if (!this.userId.equals(userId)) {
            throw new AccountAccessDeniedException();
        }
    }
}