package br.com.felipec91.application.exception_handler;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.OffsetDateTime;

@Provider
public class ApiErrorExceptionHandler implements ExceptionMapper<ApiErrorException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ApiErrorException e) {
        var apiError = new ApiErrorRecord(uriInfo.getAbsolutePath().toString(),
                400,
                Response.Status.BAD_REQUEST,
                OffsetDateTime.now(),
                e.getMessage(),
                e.getCause()
        );

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(apiError)
                .build();
    }


}
