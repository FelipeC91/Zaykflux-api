package br.com.felipec91.application.exception_handler;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(final ConstraintViolationException e) {

        var errorsMap = new HashMap<>();
        int count = 0;

        for (final var constraint : e.getConstraintViolations()) {

            var className = constraint.getLeafBean().toString().split("@")[0];
            var message = constraint.getMessage();
            var propertyPath = constraint.getPropertyPath().toString().split("\\.")[2];

            errorsMap.put(
                    count++,
                    Map.of(
                            "class", className,
                            "field", propertyPath,
                            "violationMessage", message
                    )
            );
        }

        var apiError = new ApiErrorRecord(
                uriInfo.getAbsolutePath().toString(),
                400,
                Response.Status.BAD_REQUEST,
                OffsetDateTime.now(),
                "Validation Error",
                errorsMap
        );

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(apiError)
                .build();
    }
}