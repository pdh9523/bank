package site.donghyeon.bank.infrastructure.jpa.accountTransaction.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import site.donghyeon.bank.infrastructure.jpa.accountTransaction.entity.AccountTransactionJpaEntity;

import java.util.UUID;

public interface AccountTransactionJpaRepository extends JpaRepository<AccountTransactionJpaEntity, UUID> {

    Page<AccountTransactionJpaEntity> findByAccountId(UUID accountId, Pageable pageable);
    boolean existsByEventId(UUID eventId);
}
