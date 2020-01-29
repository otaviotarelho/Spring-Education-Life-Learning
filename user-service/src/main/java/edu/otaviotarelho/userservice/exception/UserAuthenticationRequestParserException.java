package edu.otaviotarelho.userservice.exception;

public class UserAuthenticationRequestParserException extends RuntimeException {
    public UserAuthenticationRequestParserException(Exception e) {
    }

    public UserAuthenticationRequestParserException() {
        super();
    }

    public UserAuthenticationRequestParserException(String message) {
        super(message);
    }

    public UserAuthenticationRequestParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAuthenticationRequestParserException(Throwable cause) {
        super(cause);
    }

    protected UserAuthenticationRequestParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
