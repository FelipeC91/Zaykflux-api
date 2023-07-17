package service.exception;

import jakarta.ws.rs.core.UriInfo;

public class ClienteAlreadyExistsException extends ApiErrorException {

    public ClienteAlreadyExistsException(String message) {
        super(message);
    }

    public ClienteAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ClienteAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
