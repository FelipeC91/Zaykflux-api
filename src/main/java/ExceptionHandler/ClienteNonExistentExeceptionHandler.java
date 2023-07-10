package ExceptionHandler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import service.exception.ClienteNonExistentExeception;

import java.time.LocalDateTime;

@Provider
public class ClienteNonExistentExeceptionHandler implements ExceptionMapper<ClienteNonExistentExeception> {
    @Override
    public Response toResponse(ClienteNonExistentExeception clienteNonExistentExeception) {
        var apiError = new ApiError(clienteNonExistentExeception.getUri().toString(),
                                        clienteNonExistentExeception.getMessage(), Response.Status.NOT_ACCEPTABLE, LocalDateTime.now());

        return Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .entity(apiError)
                .build();
    }
}
