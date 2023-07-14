package service.exception;

import jakarta.ws.rs.core.UriInfo;

public class ClienteAlreadyExistsException extends ApiErrorException {

    public ClienteAlreadyExistsException(String message, UriInfo uri) {
        super(message, uri);
    }
}
