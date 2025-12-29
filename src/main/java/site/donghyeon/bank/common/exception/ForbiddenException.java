package site.donghyeon.bank.common.exception;

public class ForbiddenException extends APIException {
    public ForbiddenException(String message) {
        super(message);
    }
}
