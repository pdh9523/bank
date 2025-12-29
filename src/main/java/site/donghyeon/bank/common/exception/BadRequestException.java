package site.donghyeon.bank.common.exception;

public class BadRequestException extends APIException {
    public BadRequestException(String message) {
        super(message);
    }
}
