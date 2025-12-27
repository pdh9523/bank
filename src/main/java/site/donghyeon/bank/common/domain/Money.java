package site.donghyeon.bank.common.domain;

import site.donghyeon.bank.common.exception.NegativeAmountException;

public record Money(long amount) {
    public Money {
        if (amount < 0) {
            throw new NegativeAmountException(amount);
        }
    }

    public static Money zero() {
        return new Money(0);
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money subtract(Money other) {
        return new Money(this.amount - other.amount);
    }

    public boolean exceeded(Money other) {
        return this.amount > other.amount;
    }

    public Money getFee() {
        return new Money((long) (this.amount * 0.1));
    }

    public Money withFee() {
        return new Money((long) (this.amount * 1.1));
    }
}

