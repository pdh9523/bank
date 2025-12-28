-- =========================================================
--  Account / User / Transaction schema
--  PostgreSQL (Idempotent, ENUM-safe)
-- =========================================================


-- =========================================================
-- 1. ENUM TYPES
-- =========================================================

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'account_status') THEN
CREATE TYPE account_status AS ENUM (
            'OPEN',
            'CLOSED'
        );
END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'transaction_type') THEN
CREATE TYPE transaction_type AS ENUM (
            'DEPOSIT',
            'WITHDRAW',
            'TRANSFER',
            'FAIL',
            'FEE'
        );
END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'user_status') THEN
CREATE TYPE user_status AS ENUM (
            'ACTIVE',
            'DISABLED'
        );
END IF;
END $$;


-- =========================================================
-- 2. USERS
-- =========================================================

CREATE TABLE IF NOT EXISTS users (
                                     id UUID PRIMARY KEY,
                                     email VARCHAR(255) NOT NULL UNIQUE,
    status user_status NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
    );


-- =========================================================
-- 3. ACCOUNTS
-- =========================================================

CREATE TABLE IF NOT EXISTS accounts (
                                        id UUID PRIMARY KEY,
                                        user_id UUID NOT NULL,
                                        balance BIGINT NOT NULL,
                                        status account_status NOT NULL,

                                        created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_accounts_user
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    );

-- 유저의 계좌 조회
CREATE INDEX IF NOT EXISTS idx_accounts_user_status
    ON accounts (user_id, status);

-- 잔액 무결성 + CLOSED 계좌 보호 (ENUM 캐스팅 필수)
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'chk_accounts_balance_valid'
    ) THEN
ALTER TABLE accounts
    ADD CONSTRAINT chk_accounts_balance_valid
        CHECK (
            balance >= 0
                AND (
                status <> 'CLOSED'::account_status
                OR balance = 0
                )
            );
END IF;
END $$;


-- =========================================================
-- 4. ACCOUNT TRANSACTIONS
-- =========================================================

CREATE TABLE IF NOT EXISTS account_transactions (
                                                    id UUID PRIMARY KEY,
                                                    account_id UUID NOT NULL,
                                                    event_id UUID NOT NULL,
                                                    amount BIGINT NOT NULL,
                                                    balance BIGINT NOT NULL,
                                                    transaction_type transaction_type NOT NULL,

                                                    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_tx_account
    FOREIGN KEY (account_id)
    REFERENCES accounts (id)
    );

-- 계좌별 거래 조회
CREATE INDEX IF NOT EXISTS idx_tx_account_id
    ON account_transactions (account_id);

-- 멱등성 보장 (이벤트 + 계좌 + 타입)
CREATE UNIQUE INDEX IF NOT EXISTS uq_tx_event_account_type
    ON account_transactions (event_id, account_id, transaction_type);

-- =========================================================
-- END
-- =========================================================