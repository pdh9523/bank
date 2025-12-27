package site.donghyeon.bank.infrastructure.jpa.account.adapter;

import org.springframework.stereotype.Repository;
import site.donghyeon.bank.domain.account.Account;
import site.donghyeon.bank.application.account.repository.AccountRepository;
import site.donghyeon.bank.infrastructure.jpa.account.entity.AccountJpaEntity;
import site.donghyeon.bank.infrastructure.jpa.account.exception.AccountNotFoundException;
import site.donghyeon.bank.infrastructure.jpa.account.mapper.AccountMapper;

import java.util.UUID;

@Repository
public class AccountRepositoryAdapter implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    public AccountRepositoryAdapter(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Account findById(UUID accountId) {
        AccountJpaEntity entity = accountJpaRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        return AccountMapper.toDomain(entity);
    }

    @Override
    public Account save(Account account) {
        AccountJpaEntity entity = AccountMapper.toEntity(account);
        AccountJpaEntity saved = accountJpaRepository.save(entity);
        return AccountMapper.toDomain(saved);
    }

    @Override
    public boolean existsById(UUID accountId) {
        return accountJpaRepository.existsById(accountId);
    }
}
