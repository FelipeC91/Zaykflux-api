package service.exception;

import jakarta.ws.rs.core.UriInfo;

public class ContatoAlreadyLinkendWithCliente extends ApiErrorException {
    public ContatoAlreadyLinkendWithCliente(String cause, UriInfo uri) {
        super(cause, uri);
    }
}
