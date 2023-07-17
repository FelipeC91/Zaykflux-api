package service.exception;

import jakarta.ws.rs.core.UriInfo;

public class ClienteNonExistentExeception extends ApiErrorException {


    public ClienteNonExistentExeception(String message) {
        super(message);
    }

    public ClienteNonExistentExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteNonExistentExeception(Throwable cause) {
        super(cause);
    }

    public ClienteNonExistentExeception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
