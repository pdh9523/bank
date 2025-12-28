package site.donghyeon.bank.domain.accountTransaction;

import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.domain.accountTransaction.enums.TransactionType;

import java.util.UUID;

public class AccountTransaction {
    UUID txId;
    UUID eventId;
    UUID accountId;
    Money amount;
    Money balance;
    TransactionType transactionType;

    public AccountTransaction(
            UUID txId, UUID eventId, UUID accountId,
            Money amount, Money balance, TransactionType transactionType
    ) {
        this.txId = txId;
        this.eventId = eventId;
        this.accountId = accountId;
        this.amount = amount;
        this.balance = balance;
        this.transactionType = transactionType;
    }

    public static AccountTransaction deposit(
            UUID eventId, UUID accountId, Money amount, Money balance
    ) {
        return new AccountTransaction(
                UUID.randomUUID(),
                eventId,
                accountId,
                amount,
                balance,
                TransactionType.DEPOSIT
        );
    }

    public static AccountTransaction withdrawal(
            UUID eventId, UUID accountId, Money amount, Money balance
    ) {
        return new AccountTransaction(
                UUID.randomUUID(),
                eventId,
                accountId,
                amount.toMinus(),
                balance,
                TransactionType.WITHDRAW
        );
    }

    public static AccountTransaction transferFrom(
            UUID eventId, UUID accountId, Money amount, Money balance
    ) {
        return new AccountTransaction(
                UUID.randomUUID(),
                eventId,
                accountId,
                amount.toMinus(),
                balance,
                TransactionType.TRANSFER
        );
    }

    public static AccountTransaction transferTo(
            UUID eventId, UUID accountId, Money amount, Money balance
    ) {
        return new AccountTransaction(
                UUID.randomUUID(),
                eventId,
                accountId,
                amount,
                balance,
                TransactionType.TRANSFER
        );
    }

    public static AccountTransaction fee(
            UUID eventId, UUID accountId, Money amount, Money balance
    ) {
        return new AccountTransaction(
                UUID.randomUUID(),
                eventId,
                accountId,
                amount.toMinus(),
                balance,
                TransactionType.FEE
        );
    }

    public UUID getTxId() {
        return this.txId;
    }

    public UUID getAccountId() {
        return this.accountId;
    }

    public UUID getEventId() {
        return this.eventId;
    }

    public Money getAmount() {
        return this.amount;
    }

    public Money getBalance() {
        return this.balance;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public void markFailed() {
        this.transactionType = TransactionType.FAIL;
    }
}