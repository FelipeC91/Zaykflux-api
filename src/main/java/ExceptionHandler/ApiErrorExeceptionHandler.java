package ExceptionHandler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import service.exception.ApiErrorException;

import java.time.LocalDateTime;

@Provider
public class ApiErrorExeceptionHandler implements ExceptionMapper<ApiErrorException> {

    @Override
    public Response toResponse(ApiErrorException e) {
        var apiError = new ApiError(e.getUri().getPath(),
                                        e.getMessage(), Response.Status.NOT_ACCEPTABLE, LocalDateTime.now());

        return Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .entity(apiError)
                .build();
    }


}
