package site.donghyeon.bank.domain.accountTransaction;

import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.domain.accountTransaction.enums.TransactionType;

import java.util.UUID;

public class AccountTransaction {
    UUID txId;
    UUID fromAccountId;
    UUID toAccountId;
    Money amount;
    TransactionType transactionType;

    public AccountTransaction(UUID txId, UUID fromAccountId, UUID toAccountId, Money amount, TransactionType transactionType) {
        this.txId = txId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public static AccountTransaction deposit(UUID txId, UUID toAccountId, Money amount) {
        return new AccountTransaction(
                txId,
                null,
                toAccountId,
                amount,
                TransactionType.DEPOSIT
        );
    }

    public UUID getTxId() {
        return this.txId;
    }

    public UUID getFromAccountId() {
        return this.fromAccountId;
    }
    public UUID getToAccountId() {
        return this.toAccountId;
    }

    public Money getAmount() {
        return this.amount;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

}