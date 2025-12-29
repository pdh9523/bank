package site.donghyeon.bank.common.exception;

public class NegativeAmountException extends BadRequestException {
    public NegativeAmountException(long amount) {
        super("amount must >= 0, amount: %d".formatted(amount));
    }
}
