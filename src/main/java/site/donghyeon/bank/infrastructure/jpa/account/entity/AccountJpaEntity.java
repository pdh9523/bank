package site.donghyeon.bank.infrastructure.jpa.account.entity;

import jakarta.persistence.*;
import site.donghyeon.bank.domain.account.enums.AccountStatus;
import site.donghyeon.bank.infrastructure.common.BaseEntity;

import java.util.UUID;

@Entity
@Table(name = "accounts")
@Access(AccessType.FIELD)
public class AccountJpaEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "user_id", nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @Column(name = "balance", nullable = false)
    private Long balance = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    protected AccountJpaEntity() {}

    public AccountJpaEntity(UUID id, UUID userId, Long balance,  AccountStatus status) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.status = status;
    }

    public UUID getId() {
        return this.id;
    }

    public Long getBalance() {
        return this.balance;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public AccountStatus getStatus() {
        return this.status;
    }
}
