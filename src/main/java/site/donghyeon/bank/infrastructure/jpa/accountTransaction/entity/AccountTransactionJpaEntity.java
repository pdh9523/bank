package site.donghyeon.bank.infrastructure.jpa.accountTransaction.entity;

import jakarta.persistence.*;
import site.donghyeon.bank.domain.accountTransaction.enums.TransactionType;
import site.donghyeon.bank.infrastructure.common.BaseEntity;

import java.util.UUID;

//TODO: partial index 필요
@Entity
@Table(name = "account_transactions")
public class AccountTransactionJpaEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, columnDefinition="uuid")
    private UUID id;

    @Column(name = "from_account_id", columnDefinition="uuid")
    private UUID fromAccountId;

    @Column(name = "to_account_id", columnDefinition="uuid")
    private UUID toAccountId;

    @Column(name = "amount", nullable = false)
    private long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;


    public AccountTransactionJpaEntity(
            UUID id, UUID fromAccountId, UUID toAccountId,
            long amount, TransactionType transactionType
    ) {
        this.id = id;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    protected AccountTransactionJpaEntity() {}

    public UUID getId() {
        return this.id;
    }

    public UUID getFromAccountId() {
        return this.fromAccountId;
    }

    public UUID getToAccountId() {
        return this.toAccountId;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public long getAmount() {
        return this.amount;
    }
}
