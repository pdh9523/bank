package site.donghyeon.bank.common.exception;

public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }
}
