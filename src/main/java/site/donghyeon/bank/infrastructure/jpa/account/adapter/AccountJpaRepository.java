package site.donghyeon.bank.infrastructure.jpa.account.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import site.donghyeon.bank.infrastructure.jpa.account.entity.AccountJpaEntity;

import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity, UUID> {

}
