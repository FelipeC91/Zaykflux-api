package service.exception;

import jakarta.ws.rs.core.UriInfo;

public class ClienteNonExistentExeception extends ApiErrorException {


    public ClienteNonExistentExeception(String message, UriInfo uri) {
        super(message, uri);
    }
}
