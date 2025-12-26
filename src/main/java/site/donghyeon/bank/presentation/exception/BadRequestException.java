package site.donghyeon.bank.presentation.exception;

import site.donghyeon.bank.common.exception.PresentationException;

public class BadRequestException extends PresentationException {
    public BadRequestException(String message) {
        super(message);
    }
}
