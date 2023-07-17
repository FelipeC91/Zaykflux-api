package service.exception;

import jakarta.ws.rs.core.UriInfo;

public class ContatoAlreadyLinkendWithCliente extends ApiErrorException {

    public ContatoAlreadyLinkendWithCliente(String message) {
        super(message);
    }

    public ContatoAlreadyLinkendWithCliente(String message, Throwable cause) {
        super(message, cause);
    }

    public ContatoAlreadyLinkendWithCliente(Throwable cause) {
        super(cause);
    }

    public ContatoAlreadyLinkendWithCliente(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
