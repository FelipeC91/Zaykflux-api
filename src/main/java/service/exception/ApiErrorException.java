package service.exception;

import jakarta.ws.rs.core.UriInfo;

public class ApiErrorException extends RuntimeException{

    private UriInfo uri;

    public ApiErrorException(String message, UriInfo uri) {
        super(message);
        this.uri = uri;
    }

    public ApiErrorException(String message, Throwable cause, UriInfo uri) {
        super(message, cause); this.uri = uri;
    }

    public ApiErrorException(Throwable cause, UriInfo uri) {
        super(cause); this.uri = uri;
    }

     public UriInfo getUri() {return uri;}




}
