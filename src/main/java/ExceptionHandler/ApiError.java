package ExceptionHandler;

import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

public record ApiError(String path, String message, Response.Status responseStatus, LocalDateTime localDateTime) {
}
