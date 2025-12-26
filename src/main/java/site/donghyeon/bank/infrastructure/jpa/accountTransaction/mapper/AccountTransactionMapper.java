package site.donghyeon.bank.infrastructure.jpa.accountTransaction.mapper;

import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.domain.accountTransaction.AccountTransaction;
import site.donghyeon.bank.infrastructure.jpa.accountTransaction.entity.AccountTransactionJpaEntity;

public final class AccountTransactionMapper {

    public static AccountTransaction toDomain(AccountTransactionJpaEntity entity) {
        return new AccountTransaction(
                entity.getId(),
                entity.getFromAccountId(),
                entity.getToAccountId(),
                new Money(entity.getAmount()),
                entity.getTransactionType()
        );
    }

    public static AccountTransactionJpaEntity toEntity(AccountTransaction tx) {
        return new AccountTransactionJpaEntity(
                tx.getTxId(),
                tx.getFromAccountId(),
                tx.getToAccountId(),
                tx.getAmount().amount(),
                tx.getTransactionType()
        );
    }
}
